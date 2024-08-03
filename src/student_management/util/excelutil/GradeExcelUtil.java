package student_management.util.excelutil;

import student_management.model.entity.Grade;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.List;

public class GradeExcelUtil {
    private static final String GRADE_FILE_NAME = "grades.xlsx";

    public static void saveGradesToFile(List<Grade> grades) {
        String[] headers = {"学生ID", "课程ID", "成绩"};
        ExcelFileSaver.saveToFile(GRADE_FILE_NAME, grades, headers, (row, grade) -> {
            row.createCell(0).setCellValue(grade.getStudentId());
            row.createCell(1).setCellValue(grade.getCourseId());
            row.createCell(2).setCellValue(grade.getScore());
        });
    }

    public static List<Grade> loadGradesFromFile() {
        return new ArrayList<>(ExcelFileLoader.loadFromFile(GRADE_FILE_NAME, row -> {
            String studentId = CellUtil.getCellValueAsString(row.getCell(0));
            String courseId = CellUtil.getCellValueAsString(row.getCell(1));
            int score = (int) CellUtil.getNumericCellValue(row.getCell(2));
            return new Grade(studentId, courseId, score);
        }, grade -> grade.getStudentId() + "_" + grade.getCourseId()).values());
    }
}