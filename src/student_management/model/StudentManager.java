package student_management.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class StudentManager {
    private LinkedList<Student> students;
    private static final String FILE_NAME = "students.xlsx";

    public StudentManager() {
        students = new LinkedList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveToFile();
    }

    public void updateStudent(String id, String name, int age) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setAge(age);
                break;
            }
        }
        saveToFile();
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void saveToFile() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Students");
            int rowNum = 0;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getAge());

                int cellNum = 3;
                for (Map.Entry<String, Integer> entry : student.getCourses().entrySet()) {
                    row.createCell(cellNum++).setCellValue(entry.getKey());
                    row.createCell(cellNum++).setCellValue(entry.getValue());
                }
            }
            try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        students.clear();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(FILE_NAME))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                Student student = new Student(id, name, age);

                int cellNum = 3;
                while (cellNum < row.getLastCellNum()) {
                    String course = row.getCell(cellNum++).getStringCellValue();
                    int grade = (int) row.getCell(cellNum++).getNumericCellValue();
                    student.addCourse(course, grade);
                }
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String queryStudent(String id) {
        Student student = getStudent(id);
        return student != null ? student.toString() : "学生未找到";
    }

    public String queryCourse(String id, String course) {
        Student student = getStudent(id);
        if (student != null) {
            Integer grade = student.getCourses().get(course);
            return grade != null ? "课程: " + course + ", 成绩: " + grade : "课程未找到";
        }
        return "学生未找到";
    }
}