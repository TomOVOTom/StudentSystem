package student_management.util;

import org.apache.poi.ss.usermodel.*;
import student_management.model.Course;
import student_management.model.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.LinkedList;

public class StudentExcelUtil {
    private static final String STUDENT_FILE_NAME = "students.xlsx";
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_DELAY_MS = 1000;

    public static void saveStudentsToFile(LinkedList<Student> students) {
        Workbook workbook = null;
        FileOutputStream fileOut = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                workbook = ExcelUtil.createWorkbook();
                Sheet sheet = workbook.createSheet("Students");

                // 创建标题行
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("学号");
                headerRow.createCell(1).setCellValue("姓名");
                headerRow.createCell(2).setCellValue("年龄");
                headerRow.createCell(3).setCellValue("课程编号");
                headerRow.createCell(4).setCellValue("课程名称");
                headerRow.createCell(5).setCellValue("教师");
                headerRow.createCell(6).setCellValue("成绩");

                int rowNum = 1;
                for (Student student : students) {
                    for (Course course : student.getCourses().values()) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(student.getId());
                        row.createCell(1).setCellValue(student.getName());
                        row.createCell(2).setCellValue(student.getAge());
                        row.createCell(3).setCellValue(course.getCourseId());
                        row.createCell(4).setCellValue(course.getCourseName());
                        row.createCell(5).setCellValue(course.getTeacher());
                        row.createCell(6).setCellValue(course.getGrade());
                    }
                }

                fileOut = FileUtil.openFileOutputStream(STUDENT_FILE_NAME);
                channel = fileOut.getChannel();
                lock = FileUtil.lockFile(channel, false);

                ExcelUtil.saveWorkbook(workbook, fileOut);
                break; // 成功写入文件，退出循环
            } catch (IOException e) {
                e.printStackTrace();
                retries++;
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                try {
                    FileUtil.releaseFileLock(lock);
                    FileUtil.closeFileChannel(channel);
                    FileUtil.closeFileOutputStream(fileOut);
                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static LinkedList<Student> loadStudentsFromFile() {
        LinkedList<Student> students = new LinkedList<>();
        Workbook workbook = null;
        FileInputStream fileIn = null;
        FileChannel channel = null;
        FileLock lock = null;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                fileIn = FileUtil.openFileInputStream(STUDENT_FILE_NAME);
                channel = fileIn.getChannel();
                lock = FileUtil.lockFile(channel, true);

                workbook = ExcelUtil.loadWorkbook(fileIn);
                Sheet sheet = workbook.getSheetAt(0);
                boolean isHeader = true;
                for (Row row : sheet) {
                    if (isHeader) {
                        isHeader = false;
                        continue; // 跳过标题行
                    }
                    String id = ExcelUtil.getCellValueAsString(row.getCell(0));
                    String name = ExcelUtil.getCellValueAsString(row.getCell(1));
                    int age = (int) ExcelUtil.getNumericCellValue(row.getCell(2));
                    String courseId = ExcelUtil.getCellValueAsString(row.getCell(3));
                    String courseName = ExcelUtil.getCellValueAsString(row.getCell(4));
                    String teacher = ExcelUtil.getCellValueAsString(row.getCell(5));
                    int grade = (int) ExcelUtil.getNumericCellValue(row.getCell(6));

                    Student student = getStudentById(students, id);
                    if (student == null) {
                        student = new Student(id, name, age);
                        students.add(student);
                    }
                    student.addCourse(courseId, courseName, teacher, grade);
                }
                break; // 成功读取文件，退出循环
            } catch (IOException e) {
                e.printStackTrace();
                retries++;
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                try {
                    FileUtil.releaseFileLock(lock);
                    FileUtil.closeFileChannel(channel);
                    FileUtil.closeFileInputStream(fileIn);
                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    private static Student getStudentById(LinkedList<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}