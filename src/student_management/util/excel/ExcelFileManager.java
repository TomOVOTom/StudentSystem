package student_management.util.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelFileManager {
    private static final Map<String, String[]> FILE_HEADERS = new HashMap<>();

    static {
        FILE_HEADERS.put("courses.xlsx", new String[]{"课程ID", "课程名称", "教师ID", "评分方式", "学分"});
        FILE_HEADERS.put("grades.xlsx", new String[]{"学生ID", "课程ID", "成绩"});
        FILE_HEADERS.put("students.xlsx", new String[]{"学号", "姓名", "年龄", "性别", "班级ID", "班级", "院系ID", "院系"});
        FILE_HEADERS.put("users.xlsx", new String[]{"用户名", "密码", "角色"});
        FILE_HEADERS.put("student_classes.xlsx", new String[]{"班级ID", "班级名称", "院系ID"});
        FILE_HEADERS.put("departments.xlsx", new String[]{"院系ID", "院系名称"});
        FILE_HEADERS.put("teachers.xlsx", new String[]{"教师ID", "姓名", "教授科目", "年龄", "性别", "院系ID"});
        FILE_HEADERS.put("teacher_courses.xlsx", new String[]{"学生ID", "课程ID"});
    }


    public static void ensureExcelFilesExist() {
        for (String fileName : FILE_HEADERS.keySet()) {
    File file = new File(".." + File.separator + fileName);
    if (!file.exists()) {
        createExcelFileWithHeaders(".." + File.separator + fileName, FILE_HEADERS.get(fileName));
    }
}
    }

    private static void createExcelFileWithHeaders(String fileName, String[] headers) {
    try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("Sheet1");
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        File file = new File(fileName);
        file.getParentFile().mkdirs(); // 确保父目录存在
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }
        System.out.println("创建文件: " + file.getAbsolutePath());
    } catch (IOException e) {
        System.err.println("无法创建文件: " + fileName);
        e.printStackTrace();
    }
}
}