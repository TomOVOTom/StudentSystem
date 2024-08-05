package student_management.util.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
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
            File file = new File(fileName);
            System.out.println("尝试读取文件: " + file.getAbsolutePath());
            if (!file.exists() || file.length() == 0) {
                System.err.println("文件不存在或为空: " + fileName);
                return data;
            }

            fileIn = new FileInputStream(file);
            channel = fileIn.getChannel();
            lock = FileLockUtil.lockFile(channel, true);
            System.out.println("成功获取文件锁");

            workbook = WorkbookUtil.loadWorkbook(fileIn);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("成功加载工作簿,开始读取数据");
            boolean isHeader = true;
            for (Row row : sheet) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                T item = rowMapper.apply(row);
                data.put(keyExtractor.apply(item), item);
            }
            System.out.println("成功读取 " + data.size() + " 条记录");
            break;
        } catch (IOException e) {
            System.err.println("读取文件失败 (尝试 " + (retries + 1) + "/" + MAX_RETRIES + "): " + e.getMessage());
            e.printStackTrace();
            retries++;
            if (retries >= MAX_RETRIES) {
                System.err.println("达到最大重试次数，无法读取文件: " + fileName);
                return data;
            }
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
                System.out.println("成功释放文件资源");
            } catch (IOException e) {
                System.err.println("关闭资源时出错: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    return data;
}
}