package student_management.util.excelutil;

import student_management.model.entity.StudentCourse;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseExcelUtil {
    private static final String STUDENT_COURSE_FILE_NAME = "student_courses.xlsx";

    public static void saveStudentCoursesToFile(List<StudentCourse> studentCourses) {
        String[] headers = {"学生ID", "课程ID"};
        ExcelFileSaver.saveToFile(STUDENT_COURSE_FILE_NAME, studentCourses, headers, (row, studentCourse) -> {
            row.createCell(0).setCellValue(studentCourse.getStudentId());
            row.createCell(1).setCellValue(studentCourse.getCourseId());
        });
    }

    public static List<StudentCourse> loadStudentCoursesFromFile() {
        return new ArrayList<>(ExcelFileLoader.loadFromFile(STUDENT_COURSE_FILE_NAME, row -> {
            String studentId = CellUtil.getCellValueAsString(row.getCell(0));
            String courseId = CellUtil.getCellValueAsString(row.getCell(1));
            return new StudentCourse(studentId, courseId);
        }, sc -> sc.getStudentId() + "_" + sc.getCourseId()).values());
    }
}