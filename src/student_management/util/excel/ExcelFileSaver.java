package student_management.util.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.function.BiConsumer;

public class ExcelFileSaver {
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_DELAY_MS = 1000;

    public static <T> void saveToFile(String fileName, List<T> data, String[] headers, BiConsumer<Row, T> rowMapper) {
        terminateExcelProcesses(); // 终止所有 Excel 进程

        Workbook workbook = null;
        FileOutputStream fileOut = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                workbook = WorkbookUtil.createWorkbook();
                Sheet sheet = workbook.createSheet("Sheet1");

                // 创建标题行
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                }

                int rowNum = 1;
                for (T item : data) {
                    Row row = sheet.createRow(rowNum++);
                    rowMapper.accept(row, item);
                }

                fileOut = new FileOutputStream(fileName);
                channel = fileOut.getChannel();
                lock = FileLockUtil.lockFile(channel, false);

                WorkbookUtil.saveWorkbook(workbook, fileOut);
                break; // 成功写入文件，退出循环
            } catch (IOException e) {
                e.printStackTrace();
                retries++;
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                try {
                    FileLockUtil.releaseFileLock(lock);
                    FileLockUtil.closeFileChannel(channel);
                    FileLockUtil.closeFileOutputStream(fileOut);
                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void terminateExcelProcesses() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("taskkill /F /IM excel.exe");
            } else if (os.contains("mac")) {
                process = Runtime.getRuntime().exec("pkill -f Excel");
            } else if (os.contains("nix") || os.contains("nux")) {
                process = Runtime.getRuntime().exec("pkill -f Excel");
            } else {
                throw new UnsupportedOperationException("Unsupported operating system: " + os);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}