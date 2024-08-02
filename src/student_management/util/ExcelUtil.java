package student_management.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ExcelUtil {
    public static Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    public static Workbook loadWorkbook(FileInputStream fileIn) throws IOException {
        return new XSSFWorkbook(fileIn);
    }

    public static void saveWorkbook(Workbook workbook, FileOutputStream fileOut) throws IOException {
        workbook.write(fileOut);
    }

    public static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return 0;
    }

    public static <T> void saveToFile(String fileName, List<T> data, String[] headers, BiConsumer<Row, T> rowMapper) {
        Workbook workbook = null;
        FileOutputStream fileOut = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;
        final int MAX_RETRIES = 5;
        final int RETRY_DELAY_MS = 1000;

        while (retries < MAX_RETRIES) {
            try {
                workbook = createWorkbook();
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

                fileOut = FileUtil.openFileOutputStream(fileName);
                channel = fileOut.getChannel();
                lock = FileUtil.lockFile(channel, false);

                saveWorkbook(workbook, fileOut);
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
                    FileUtil.releaseFileLock(lock);
                    FileUtil.closeFileChannel(channel);
                    FileUtil.closeFileOutputStream(fileOut);
                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> Map<String, T> loadFromFile(String fileName, Function<Row, T> rowMapper, Function<T, String> keyExtractor) {
        Map<String, T> data = new HashMap<>();
        Workbook workbook = null;
        FileInputStream fileIn = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;
        final int MAX_RETRIES = 5;
        final int RETRY_DELAY_MS = 1000;

        while (retries < MAX_RETRIES) {
            try {
                fileIn = FileUtil.openFileInputStream(fileName);
                channel = fileIn.getChannel();
                lock = FileUtil.lockFile(channel, true);

                workbook = loadWorkbook(fileIn);
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
                    FileUtil.releaseFileLock(lock);
                    FileUtil.closeFileChannel(channel);
                    FileUtil.closeFileInputStream(fileIn);
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