package student_management.server.handlers;

import student_management.model.entity.User;
import student_management.model.manager.UserManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;

public class UserHandler {
    private UserManager userManager;
    private Logger logger;

    public UserHandler(UserManager userManager, Logger logger) {
        this.userManager = userManager;
        this.logger = logger;
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
            case "QUERY_ALL":
                return queryAllUsers(ois);
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
        User currentUser = (User) ois.readObject();
        String username = (String) ois.readObject();

        logger.log("查询用户. 当前用户: " + currentUser.getUsername() + ", 查询用户名: " + username);

        if (!currentUser.getRole().equals("admin")) {
            return "错误: 只有管理员可以查询用户信息";
        }

        User user = userManager.getUser(username);
        return user != null ? user.toString() : "未找到用户: " + username;
    }

    private String queryAllUsers(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User currentUser = (User) ois.readObject();

        if (!currentUser.getRole().equals("admin")) {
            return "错误: 只有管理员可以查询所有用户信息";
        }
        List<User> allUsers = userManager.getAllUsers();
        logger.log("查询所有用户,总数: " + allUsers.size());
        return allUsers.stream()
                .map(User::toString)
                .collect(Collectors.joining("\n"));
    }


}
