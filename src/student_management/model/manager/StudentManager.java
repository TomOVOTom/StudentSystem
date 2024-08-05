package student_management.model.manager;

import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.StudentExcelUtil;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentManager {
    private LinkedList<Student> students;
    private Logger logger;

    public StudentManager(Logger logger) {
        this.students = new LinkedList<>();
        this.logger = logger;
        loadStudentsFromFile();
    }

    public void addStudent(Student student, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        students.add(student);
        saveStudentsToFile();
        logger.log("用户 " + user.getUsername() + " 添加学生: " + student.toString());
    }

    public void removeStudent(String id, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (removed) {
            saveStudentsToFile();
            logger.log("用户 " + user.getUsername() + " 删除学生: " + id);
        } else {
            logger.log("用户 " + user.getUsername() + " 删除学生失败，未找到学生: " + id);
        }
    }

    public void updateStudent(Student updatedStudent, User user) {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        students.stream()
                .filter(student -> student.getId().equals(updatedStudent.getId()))
                .findFirst()
                .ifPresent(student -> {
                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    student.setGender(updatedStudent.getGender());
                    student.setClassId(updatedStudent.getClassId());
                    student.setClassName(updatedStudent.getClassName());
                    student.setDepartmentId(updatedStudent.getDepartmentId());
                    student.setDepartmentName(updatedStudent.getDepartmentName());
                    saveStudentsToFile();
                    logger.log("用户 " + user.getUsername() + " 更新学生: " + updatedStudent.getId());
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

    public String queryAllStudents() {
        return students.stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
    }
}