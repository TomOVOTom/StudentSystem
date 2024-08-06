package student_management.server.handlers;

import student_management.model.entity.Teacher;
import student_management.model.entity.User;
import student_management.model.manager.TeacherManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TeacherHandler {
    private TeacherManager teacherManager;
    private Logger logger;

    public TeacherHandler(TeacherManager teacherManager, Logger logger) {
        this.teacherManager = teacherManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
    User user = (User) ois.readObject();
    try {
        switch (command) {
            case "TEACHER_ADD_TEACHER":
                return addTeacher(ois, user);
            case "TEACHER_REMOVE_TEACHER":
                return removeTeacher(ois, user);
            case "TEACHER_UPDATE_TEACHER":
                return updateTeacher(ois, user);
            case "TEACHER_QUERY_TEACHER":
                return queryTeacher(ois, user);
            case "TEACHER_QUERY_ALL_TEACHERS":
                return queryAllTeachers(user);
            default:
                return "未知命令";
        }
    } catch (Exception e) {
        logger.log("错误: " + e.getMessage());
        return "操作失败: " + e.getMessage();
    }
}

    private String addTeacher(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Teacher teacher = (Teacher) ois.readObject();
        teacherManager.addTeacher(teacher);
        logger.log("用户 " + user.getUsername() + " 添加了老师: " + teacher.toString());
        return "老师添加成功";
    }

    private String removeTeacher(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String id = (String) ois.readObject();
        teacherManager.removeTeacher(id);
        logger.log("用户 " + user.getUsername() + " 删除了老师: " + id);
        return "老师删除成功";
    }

    private String updateTeacher(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Teacher teacher = (Teacher) ois.readObject();
        teacherManager.updateTeacher(teacher);
        logger.log("用户 " + user.getUsername() + " 更新了老师: " + teacher.toString());
        return "老师更新成功";
    }

    private String queryTeacher(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String id = (String) ois.readObject();
        String result = teacherManager.queryTeacher(id);
        logger.log("用户 " + user.getUsername() + " 查询了老师: " + id);
        return result;
    }

    private String queryAllTeachers(User user) {
        String result = teacherManager.queryAllTeachers();
        logger.log("用户 " + user.getUsername() + " 查询了所有老师");
        return result;
    }
}