package student_management.util.excelutil;

import student_management.model.entity.Course;
import student_management.model.entity.Student;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.LinkedList;
import java.util.Map;

public class StudentExcelUtil {
    private static final String STUDENT_FILE_NAME = "students.xlsx";

    public static void saveStudentsToFile(LinkedList<Student> students) {
        String[] headers = {"学号", "姓名", "年龄", "班级", "院系", "课程编号", "课程名称", "教师", "评分方式", "成绩"};
        ExcelFileSaver.saveToFile(STUDENT_FILE_NAME, students, headers, (row, student) -> {
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(student.getId());
            row.createCell(cellIndex++).setCellValue(student.getName());
            row.createCell(cellIndex++).setCellValue(student.getAge());
            row.createCell(cellIndex++).setCellValue(student.getClassId());
            row.createCell(cellIndex++).setCellValue(student.getDepartmentId());
            for (Course course : student.getCourses().values()) {
                row.createCell(cellIndex++).setCellValue(course.getCourseId());
                row.createCell(cellIndex++).setCellValue(course.getCourseName());
                row.createCell(cellIndex++).setCellValue(course.getTeacherId());
                row.createCell(cellIndex++).setCellValue(course.getGradingSystem());
                row.createCell(cellIndex++).setCellValue(course.getGrade());
            }
        });
    }

    public static LinkedList<Student> loadStudentsFromFile() {
        Map<String, Student> studentsMap = ExcelFileLoader.loadFromFile(STUDENT_FILE_NAME, row -> {
            String id = CellUtil.getCellValueAsString(row.getCell(0));
            String name = CellUtil.getCellValueAsString(row.getCell(1));
            int age = (int) CellUtil.getNumericCellValue(row.getCell(2));
            String classId = CellUtil.getCellValueAsString(row.getCell(3));
            String departmentId = CellUtil.getCellValueAsString(row.getCell(4));
            Student student = new Student(id, name, age, classId, departmentId);
            String courseId = CellUtil.getCellValueAsString(row.getCell(5));
            String courseName = CellUtil.getCellValueAsString(row.getCell(6));
            String teacher = CellUtil.getCellValueAsString(row.getCell(7));
            String gradingSystem = CellUtil.getCellValueAsString(row.getCell(8));
            int grade = (int) CellUtil.getNumericCellValue(row.getCell(9));
            Course course = new Course(courseId, courseName, teacher, gradingSystem,0.0f);
            course.setGrade(grade);
            student.addCourse(course);
            return student;
        }, Student::getId);
        return new LinkedList<>(studentsMap.values());
    }
}