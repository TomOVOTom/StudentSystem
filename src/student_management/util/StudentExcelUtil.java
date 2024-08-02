package student_management.util;

import student_management.model.Course;
import student_management.model.Student;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.LinkedList;
import java.util.Map;

public class StudentExcelUtil {
    private static final String STUDENT_FILE_NAME = "students.xlsx";

    public static void saveStudentsToFile(LinkedList<Student> students) {
        String[] headers = {"学号", "姓名", "年龄", "课程编号", "课程名称", "教师", "成绩"};
        ExcelFileSaver.saveToFile(STUDENT_FILE_NAME, students, headers, (row, student) -> {
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(student.getId());
            row.createCell(cellIndex++).setCellValue(student.getName());
            row.createCell(cellIndex++).setCellValue(student.getAge());
            for (Course course : student.getCourses().values()) {
                row.createCell(cellIndex++).setCellValue(course.getCourseId());
                row.createCell(cellIndex++).setCellValue(course.getCourseName());
                row.createCell(cellIndex++).setCellValue(course.getTeacher());
                row.createCell(cellIndex++).setCellValue(course.getGrade());
            }
        });
    }

    public static LinkedList<Student> loadStudentsFromFile() {
        Map<String, Student> studentsMap = ExcelFileLoader.loadFromFile(STUDENT_FILE_NAME, row -> {
            String id = CellUtil.getCellValueAsString(row.getCell(0));
            String name = CellUtil.getCellValueAsString(row.getCell(1));
            int age = (int) CellUtil.getNumericCellValue(row.getCell(2));
            Student student = new Student(id, name, age);
            String courseId = CellUtil.getCellValueAsString(row.getCell(3));
            String courseName = CellUtil.getCellValueAsString(row.getCell(4));
            String teacher = CellUtil.getCellValueAsString(row.getCell(5));
            int grade = (int) CellUtil.getNumericCellValue(row.getCell(6));
            student.addCourse(courseId, courseName, teacher, grade);
            return student;
        }, Student::getId);
        return new LinkedList<>(studentsMap.values());
    }
}