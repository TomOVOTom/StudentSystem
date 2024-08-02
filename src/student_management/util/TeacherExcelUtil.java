package student_management.util;

import org.apache.poi.ss.usermodel.*;
import student_management.model.Teacher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

public class TeacherExcelUtil {
    private static final String TEACHER_FILE_NAME = "teachers.xlsx";
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_DELAY_MS = 1000;

    public static void saveTeachersToFile(HashMap<String, Teacher> teachers) {
        Workbook workbook = null;
        FileOutputStream fileOut = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                workbook = ExcelUtil.createWorkbook();
                Sheet sheet = workbook.createSheet("Teachers");

                // 创建标题行
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("教师编号");
                headerRow.createCell(1).setCellValue("姓名");
                headerRow.createCell(2).setCellValue("科目");

                int rowNum = 1;
                for (Teacher teacher : teachers.values()) {
                    Row row = sheet.createRow(rowNum);
                    row.createCell(0).setCellValue(teacher.getId());
                    row.createCell(1).setCellValue(teacher.getName());
                    row.createCell(2).setCellValue(teacher.getSubject());
                    rowNum++;
                }

                fileOut = FileUtil.openFileOutputStream(TEACHER_FILE_NAME);
                channel = fileOut.getChannel();
                lock = FileUtil.lockFile(channel, false);

                ExcelUtil.saveWorkbook(workbook, fileOut);
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

    public static HashMap<String, Teacher> loadTeachersFromFile() {
        HashMap<String, Teacher> teachers = new HashMap<>();
        Workbook workbook = null;
        FileInputStream fileIn = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                fileIn = FileUtil.openFileInputStream(TEACHER_FILE_NAME);
                channel = fileIn.getChannel();
                lock = FileUtil.lockFile(channel, true);

                workbook = ExcelUtil.loadWorkbook(fileIn);
                Sheet sheet = workbook.getSheetAt(0);
                boolean isHeader = true;
                for (Row row : sheet) {
                    if (isHeader) {
                        isHeader = false;
                        continue; // 跳过标题行
                    }
                    String id = ExcelUtil.getCellValueAsString(row.getCell(0));
                    String name = ExcelUtil.getCellValueAsString(row.getCell(1));
                    String subject = ExcelUtil.getCellValueAsString(row.getCell(2));

                    Teacher teacher = new Teacher(id, name, subject);
                    teachers.put(id, teacher);
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
        return teachers;
    }
}