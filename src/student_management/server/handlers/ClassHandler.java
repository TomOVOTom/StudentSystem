package student_management.server.handlers;

import student_management.model.entity.Class;
import student_management.model.entity.User;
import student_management.model.manager.ClassManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClassHandler {
    private ClassManager classManager;
    private Logger logger;

    public ClassHandler(ClassManager classManager, Logger logger) {
        this.classManager = classManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "CLASS_ADD_CLASS":
                    return addClass(ois, user);
                case "CLASS_REMOVE_CLASS":
                    return removeClass(ois, user);
                case "CLASS_UPDATE_CLASS":
                    return updateClass(ois, user);
                case "CLASS_QUERY_CLASS":
                    return queryClass(ois, user);
                case "CLASS_QUERY_ALL_CLASSES":
                    return queryAllClasses(user);
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

    private String addClass(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Class aClass = (Class) ois.readObject();
        classManager.addClass(aClass);
        logger.log("用户 " + user.getUsername() + " 添加了班级: " + aClass.toString());
        return "班级添加成功";
    }

    private String removeClass(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String classId = (String) ois.readObject();
        classManager.removeClass(classId);
        logger.log("用户 " + user.getUsername() + " 删除了班级: " + classId);
        return "班级删除成功";
    }

    private String updateClass(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Class aClass = (Class) ois.readObject();
        classManager.updateClass(aClass);
        logger.log("用户 " + user.getUsername() + " 更新了班级: " + aClass.toString());
        return "班级更新成功";
    }

    private String queryClass(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String classId = (String) ois.readObject();
        return classManager.queryClass(classId);
    }

    private String queryAllClasses(User user) {
        return classManager.queryAllClasses();
    }
}