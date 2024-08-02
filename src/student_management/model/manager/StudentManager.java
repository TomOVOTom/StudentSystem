package student_management.model.manager;

import student_management.model.entity.Course;
import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.util.LoggerUtil;
import student_management.util.StudentExcelUtil;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentManager {
    private LinkedList<Student> students;

    public StudentManager() {
        students = new LinkedList<>();
        loadStudentsFromFile();
    }

    public void addStudent(Student student, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        students.add(student);
        saveStudentsToFile();
        LoggerUtil.log("添加学生: " + student.toString());
    }

    public void removeStudent(String id, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (removed) {
            saveStudentsToFile();
            LoggerUtil.log("删除学生: " + id);
        } else {
            LoggerUtil.log("删除学生失败，未找到学生: " + id);
        }
    }

    public void updateStudent(String id, String name, int age, String classId, String departmentId, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .ifPresent(student -> {
                    student.setName(name);
                    student.setAge(age);
                    student.setClassId(classId);
                    student.setDepartmentId(departmentId);
                    saveStudentsToFile();
                    LoggerUtil.log("更新学生: " + id);
                });
    }

    public Optional<Student> getStudent(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public LinkedList<Student> getStudents() {
        return new LinkedList<>(students);
    }

    public void saveStudentsToFile() {
        StudentExcelUtil.saveStudentsToFile(students);
    }

    public void loadStudentsFromFile() {
        students = StudentExcelUtil.loadStudentsFromFile();
    }

    public String queryStudent(String id) {
        return getStudent(id)
                .map(Student::toString)
                .orElse("学生未找到");
    }

    public void addCourse(String studentId, String courseId, String courseName, String teacher, int grade, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        getStudent(studentId).ifPresent(student -> {
            Course course = new Course(courseId, courseName, teacher, grade);
            student.addCourse(course);
            saveStudentsToFile();
            LoggerUtil.log("为学生 " + studentId + " 添加课程: " + course.toString());
        });
    }


    public String queryCourse(String id, String courseId) {
        return getStudent(id)
                .map(student -> Optional.ofNullable(student.getCourses().get(courseId))
                        .map(Course::toString)
                        .orElse("课程未找到"))
                .orElse("学生未找到");
    }

    public String queryAllStudents() {
        return students.stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
    }
}