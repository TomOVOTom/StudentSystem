package student_management.util.excelutil;

import student_management.model.entity.Teacher;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherExcelUtil {
    public static final String TEACHER_FILE_NAME = "teachers.xlsx";

    public static void saveTeachersToFile(HashMap<String, Teacher> teachers) {
    System.out.println("开始保存教师数据到 Excel 文件");
    String[] headers = {"教师编号", "姓名", "教授科目", "年龄", "性别", "院系ID"};
    List<Teacher> teacherList = new ArrayList<>(teachers.values());
    ExcelFileSaver.saveToFile(TEACHER_FILE_NAME, teacherList, headers, (row, teacher) -> {
        row.createCell(0).setCellValue(teacher.getId());
        row.createCell(1).setCellValue(teacher.getName());
        row.createCell(2).setCellValue(teacher.getSubject());
        row.createCell(3).setCellValue(teacher.getAge());
        row.createCell(4).setCellValue(teacher.getGender());
        row.createCell(5).setCellValue(teacher.getDepartmentId());
    });
    System.out.println("教师数据保存完成，共保存 " + teachers.size() + " 条记录");
}

public static HashMap<String, Teacher> loadTeachersFromFile() {
    System.out.println("开始从文件加载教师数据: " + TEACHER_FILE_NAME);
    File file = new File(TEACHER_FILE_NAME);
    System.out.println("文件绝对路径: " + file.getAbsolutePath());
    if (!file.exists()) {
        System.out.println("教师文件不存在: " + file.getAbsolutePath());
        return new HashMap<>();
    }
    if (!file.canRead()) {
        System.out.println("无法读取教师文件: " + file.getAbsolutePath());
        return new HashMap<>();
    }
    System.out.println("教师文件存在,开始读取数据");
    HashMap<String, Teacher> teachers = (HashMap<String, Teacher>) ExcelFileLoader.loadFromFile(TEACHER_FILE_NAME, row -> {
        String id = CellUtil.getCellValueAsString(row.getCell(0));
        String name = CellUtil.getCellValueAsString(row.getCell(1));
        String subject = CellUtil.getCellValueAsString(row.getCell(2));
        int age = (int) CellUtil.getNumericCellValue(row.getCell(3));
        String gender = CellUtil.getCellValueAsString(row.getCell(4));
        String departmentId = CellUtil.getCellValueAsString(row.getCell(5));
        System.out.println("读取到教师数据: " + id + ", " + name + ", " + subject + ", " + age + ", " + gender + ", " + departmentId);
        return new Teacher(id, name, subject, age, gender, departmentId);
    }, Teacher::getId);
    System.out.println("从文件加载了 " + teachers.size() + " 条教师记录");
    return teachers;
}
}