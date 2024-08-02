package student_management.server;

import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.model.manager.StudentManager;
import student_management.util.LoggerUtil;

import java.io.IOException;
import java.io.ObjectInputStream;

public class StudentCommandHandler {
    private StudentManager studentManager;

    public StudentCommandHandler(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject(); // 读取用户信息
        try {
            switch (command) {
                case "STUDENT_ADD_STUDENT": {
                    Student student = (Student) ois.readObject();
                    studentManager.addStudent(student, user);
                    LoggerUtil.log("用户 " + user.getUsername() + " 添加了学生: " + student.getId());
                    return "学生添加成功";
                }
                case "STUDENT_REMOVE_STUDENT": {
                    String id = (String) ois.readObject();
                    studentManager.removeStudent(id, user);
                    LoggerUtil.log("用户 " + user.getUsername() + " 删除了学生: " + id);
                    return "学生删除成功";
                }
                case "STUDENT_UPDATE_STUDENT": {
                    String id = (String) ois.readObject();
                    String name = (String) ois.readObject();
                    int age = (int) ois.readObject();
                    String classId = (String) ois.readObject();
                    String departmentId = (String) ois.readObject();
                    studentManager.updateStudent(id, name, age, classId, departmentId, user);
                    LoggerUtil.log("用户 " + user.getUsername() + " 更新了学生: " + id);
                    return "学生更新成功";
                }
                case "STUDENT_REMOVE_COURSE": {
                    String id = (String) ois.readObject();
                    String courseId = (String) ois.readObject();
                    Student student = studentManager.getStudent(id).orElse(null);
                    if (student != null) {
                        student.removeCourse(courseId);
                        studentManager.saveStudentsToFile();
                        LoggerUtil.log("用户 " + user.getUsername() + " 为学生 " + id + " 删除了课程: " + courseId);
                        return "课程删除成功";
                    } else {
                        return "学生未找到";
                    }
                }
                case "STUDENT_UPDATE_COURSE": {
                    String id = (String) ois.readObject();
                    String courseId = (String) ois.readObject();
                    String courseName = (String) ois.readObject();
                    String teacher = (String) ois.readObject();
                    int grade = (int) ois.readObject();
                    Student student = studentManager.getStudent(id).orElse(null);
                    if (student != null) {
                        student.updateCourse(courseId, courseName, teacher, grade);
                        studentManager.saveStudentsToFile();
                        LoggerUtil.log("用户 " + user.getUsername() + " 为学生 " + id + " 更新了课程: " + courseId);
                        return "课程更新成功";
                    } else {
                        return "学生未找到";
                    }
                }
                case "STUDENT_QUERY_STUDENT": {
                    String id = (String) ois.readObject();
                    LoggerUtil.log("用户 " + user.getUsername() + " 查询了学生: " + id);
                    return studentManager.queryStudent(id);
                }
                case "STUDENT_QUERY_COURSE": {
                    String id = (String) ois.readObject();
                    String courseId = (String) ois.readObject();
                    LoggerUtil.log("用户 " + user.getUsername() + " 查询了学生 " + id + " 的课程: " + courseId);
                    return studentManager.queryCourse(id, courseId);
                }
                case "STUDENT_QUERY_ALL_STUDENTS": {
                    LoggerUtil.log("用户 " + user.getUsername() + " 查询了所有学生");
                    return studentManager.queryAllStudents();
                }
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            LoggerUtil.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }
}