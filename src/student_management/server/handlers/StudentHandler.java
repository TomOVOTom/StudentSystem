package student_management.server.handlers;

import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.model.manager.StudentManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class StudentHandler {
    private StudentManager studentManager;
    private Logger logger;

    public StudentHandler(StudentManager studentManager, Logger logger) {
        this.studentManager = studentManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "STUDENT_ADD_STUDENT":
                    return handleAddStudent(ois, user);
                case "STUDENT_REMOVE_STUDENT":
                    return handleRemoveStudent(ois, user);
                case "STUDENT_UPDATE_STUDENT":
                    return handleUpdateStudent(ois, user);
                case "STUDENT_QUERY_STUDENT":
                    return handleQueryStudent(ois, user);
                case "STUDENT_QUERY_ALL_STUDENTS":
                    return handleQueryAllStudents(user);
                    default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

   private String handleAddStudent(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
    Student student = (Student) ois.readObject();
    studentManager.addStudent(student, user);
    logger.log("用户 " + user.getUsername() + " 添加了学生: " + student.getId());
    return "学生添加成功";
}

    private String handleRemoveStudent(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String id = (String) ois.readObject();
        studentManager.removeStudent(id, user);
        logger.log("用户 " + user.getUsername() + " 删除了学生: " + id);
        return "学生删除成功";
    }

    private String handleUpdateStudent(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        Student student = (Student) ois.readObject();
        studentManager.updateStudent(student, user);
        logger.log("用户 " + user.getUsername() + " 更新了学生: " + student.getId());
        return "学生更新成功";
    }

    private String handleQueryStudent(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String id = (String) ois.readObject();
        logger.log("用户 " + user.getUsername() + " 查询了学生: " + id);
        return studentManager.queryStudent(id);
    }

    private String handleQueryAllStudents(User user) {
        logger.log("用户 " + user.getUsername() + " 查询了所有学生");
        return studentManager.queryAllStudents();
    }

}