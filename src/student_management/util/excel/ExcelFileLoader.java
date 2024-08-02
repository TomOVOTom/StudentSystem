package student_management.util.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExcelFileLoader {
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_DELAY_MS = 1000;

    public static <T> Map<String, T> loadFromFile(String fileName, Function<Row, T> rowMapper, Function<T, String> keyExtractor) {
        Map<String, T> data = new HashMap<>();
        Workbook workbook = null;
        FileInputStream fileIn = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                fileIn = new FileInputStream(fileName);
                channel = fileIn.getChannel();
                lock = FileLockUtil.lockFile(channel, true);

                workbook = WorkbookUtil.loadWorkbook(fileIn);
                Sheet sheet = workbook.getSheetAt(0);
                boolean isHeader = true;
                for (Row row : sheet) {
                    if (isHeader) {
                        isHeader = false;
                        continue; // 跳过标题行
                    }
                    T item = rowMapper.apply(row);
                    data.put(keyExtractor.apply(item), item);
                }
                break; // 成功读取文件，退出循环
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
                    FileLockUtil.closeFileInputStream(fileIn);
                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}