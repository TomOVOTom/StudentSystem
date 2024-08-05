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
        String[] headers = {"学号", "姓名", "年龄", "性别", "班级ID", "班级", "院系ID", "院系"};
        ExcelFileSaver.saveToFile(STUDENT_FILE_NAME, students, headers, (row, student) -> {
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(student.getId());
            row.createCell(cellIndex++).setCellValue(student.getName());
            row.createCell(cellIndex++).setCellValue(student.getAge());
            row.createCell(cellIndex++).setCellValue(student.getGender());
            row.createCell(cellIndex++).setCellValue(student.getClassId());
            row.createCell(cellIndex++).setCellValue(student.getClassName());
            row.createCell(cellIndex++).setCellValue(student.getDepartmentId());
            row.createCell(cellIndex++).setCellValue(student.getDepartmentName());
        });
    }


    public static LinkedList<Student> loadStudentsFromFile() {
        Map<String, Student> studentsMap = ExcelFileLoader.loadFromFile(STUDENT_FILE_NAME, row -> {
            String id = CellUtil.getCellValueAsString(row.getCell(0));
            String name = CellUtil.getCellValueAsString(row.getCell(1));
            int age = (int) CellUtil.getNumericCellValue(row.getCell(2));
            String gender = CellUtil.getCellValueAsString(row.getCell(3));
            String classId = CellUtil.getCellValueAsString(row.getCell(4));
            String className = CellUtil.getCellValueAsString(row.getCell(5));
            String departmentId = CellUtil.getCellValueAsString(row.getCell(6));
            String departmentName = CellUtil.getCellValueAsString(row.getCell(7));
            return new Student(id, name, age, gender, classId, className, departmentId, departmentName);
        }, Student::getId);
        return new LinkedList<>(studentsMap.values());
    }
}