package student_management.server.handlers;

import student_management.model.entity.User;
import student_management.model.manager.UserManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class UserHandler {
    private UserManager userManager;

    public UserHandler(UserManager userManager, Logger logger) {
        this.userManager = userManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String[] parts = command.split("_");
        String action = parts[1];

        switch (action) {
            case "ADD":
                return addUser(ois);
            case "REMOVE":
                return removeUser(ois);
            case "UPDATE":
                return updateUser(ois);
            case "QUERY":
                return queryUser(ois);
            default:
                return "未知的用户命令";
        }
    }

    private String addUser(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        userManager.addUser(user);
        return "用户添加成功";
    }

    private String removeUser(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String username = (String) ois.readObject();
        userManager.removeUser(username);
        return "用户删除成功";
    }

    private String updateUser(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        userManager.updateUser(user);
        return "用户更新成功";
    }

    private String queryUser(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String username = (String) ois.readObject();
        User user = userManager.getUser(username);
        return user != null ? user.toString() : "用户不存在";
    }
}