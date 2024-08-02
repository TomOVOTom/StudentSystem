package student_management.model;

import student_management.util.LoggerUtil;
import student_management.util.StudentExcelUtil;

import java.util.LinkedList;

public class StudentManager {
    private LinkedList<Student> students;

    public StudentManager() {
        students = new LinkedList<>();
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        LoggerUtil.log("添加学生: " + student.toString());
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveStudentsToFile();
        LoggerUtil.log("删除学生: " + id);
    }

    public void updateStudent(String id, String name, int age) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setAge(age);
                break;
            }
        }
        saveStudentsToFile();
        LoggerUtil.log("更新学生: " + id);
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

    public void saveStudentsToFile() {
        StudentExcelUtil.saveStudentsToFile(students);
    }

    public void loadStudentsFromFile() {
        students = StudentExcelUtil.loadStudentsFromFile();
    }

    public String queryStudent(String id) {
        Student student = getStudent(id);
        return student != null ? student.toString() : "学生未找到";
    }

    public String queryCourse(String id, String courseId) {
        Student student = getStudent(id);
        if (student != null) {
            Course course = student.getCourses().get(courseId);
            return course != null ? course.toString() : "课程未找到";
        }
        return "学生未找到";
    }
}