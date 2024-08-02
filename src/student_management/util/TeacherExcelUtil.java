package student_management.util;

import student_management.model.Teacher;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherExcelUtil {
    private static final String TEACHER_FILE_NAME = "teachers.xlsx";

    public static void saveTeachersToFile(HashMap<String, Teacher> teachers) {
        String[] headers = {"教师编号", "姓名", "科目"};
        List<Teacher> teacherList = new ArrayList<>(teachers.values());
        ExcelFileSaver.saveToFile(TEACHER_FILE_NAME, teacherList, headers, (row, teacher) -> {
            row.createCell(0).setCellValue(teacher.getId());
            row.createCell(1).setCellValue(teacher.getName());
            row.createCell(2).setCellValue(teacher.getSubject());
        });
    }

    public static HashMap<String, Teacher> loadTeachersFromFile() {
        return (HashMap<String, Teacher>) ExcelFileLoader.loadFromFile(TEACHER_FILE_NAME, row -> {
            String id = CellUtil.getCellValueAsString(row.getCell(0));
            String name = CellUtil.getCellValueAsString(row.getCell(1));
            String subject = CellUtil.getCellValueAsString(row.getCell(2));
            return new Teacher(id, name, subject);
        }, Teacher::getId);
    }
}