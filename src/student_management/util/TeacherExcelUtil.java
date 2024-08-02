package student_management.util;

import student_management.model.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherExcelUtil {
    private static final String TEACHER_FILE_NAME = "teachers.xlsx";

    public static void saveTeachersToFile(HashMap<String, Teacher> teachers) {
        String[] headers = {"教师编号", "姓名", "科目"};
        List<Teacher> teacherList = new ArrayList<>(teachers.values());
        ExcelUtil.saveToFile(TEACHER_FILE_NAME, teacherList, headers, (row, teacher) -> {
            row.createCell(0).setCellValue(teacher.getId());
            row.createCell(1).setCellValue(teacher.getName());
            row.createCell(2).setCellValue(teacher.getSubject());
        });
    }

    public static HashMap<String, Teacher> loadTeachersFromFile() {
        return (HashMap<String, Teacher>) ExcelUtil.loadFromFile(TEACHER_FILE_NAME, row -> {
            String id = ExcelUtil.getCellValueAsString(row.getCell(0));
            String name = ExcelUtil.getCellValueAsString(row.getCell(1));
            String subject = ExcelUtil.getCellValueAsString(row.getCell(2));
            return new Teacher(id, name, subject);
        }, Teacher::getId);
    }
}