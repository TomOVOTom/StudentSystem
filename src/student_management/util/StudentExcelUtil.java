package student_management.util;

import student_management.model.Course;
import student_management.model.Student;

import java.util.LinkedList;
import java.util.Map;

public class StudentExcelUtil {
    private static final String STUDENT_FILE_NAME = "students.xlsx";

    public static void saveStudentsToFile(LinkedList<Student> students) {
        String[] headers = {"学号", "姓名", "年龄", "课程编号", "课程名称", "教师", "成绩"};
        ExcelUtil.saveToFile(STUDENT_FILE_NAME, students, headers, (row, student) -> {
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
        Map<String, Student> studentsMap = ExcelUtil.loadFromFile(STUDENT_FILE_NAME, row -> {
            String id = ExcelUtil.getCellValueAsString(row.getCell(0));
            String name = ExcelUtil.getCellValueAsString(row.getCell(1));
            int age = (int) ExcelUtil.getNumericCellValue(row.getCell(2));
            Student student = new Student(id, name, age);
            String courseId = ExcelUtil.getCellValueAsString(row.getCell(3));
            String courseName = ExcelUtil.getCellValueAsString(row.getCell(4));
            String teacher = ExcelUtil.getCellValueAsString(row.getCell(5));
            int grade = (int) ExcelUtil.getNumericCellValue(row.getCell(6));
            student.addCourse(courseId, courseName, teacher, grade);
            return student;
        }, Student::getId);
        return new LinkedList<>(studentsMap.values());
    }
}