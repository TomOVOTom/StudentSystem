package student_management.model.manager;

import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.UserExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private HashMap<String, User> users;
    private Logger logger;

  public UserManager(Logger logger) {
    this.logger = logger;
    loadUsersFromFile();
    logger.log("UserManager 初始化完成,加载了 " + users.size() + " 个用户");
}

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
        logger.log("添加用户: " + user.getUsername());

        // 立即重新加载并验证
        loadUsersFromFile();
        if (users.containsKey(user.getUsername())) {
            logger.log("用户成功添加并验证: " + user.getUsername());
        } else {
            logger.log("警告：用户添加失败或未能验证: " + user.getUsername());
        }
    }

    public void removeUser(String username) {
        users.remove(username);
        saveUsersToFile();
        logger.log("删除用户: " + username);
    }

    public void updateUser(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
        logger.log("更新用户: " + user.getUsername());
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public Optional<User> authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.log("用户认证成功: " + username);
            return Optional.of(user);
        }
        logger.log("用户认证失败: " + username);
        return Optional.empty();
    }

    private void loadUsersFromFile() {
        users = UserExcelUtil.loadUsersFromFile();
        logger.log("从文件加载用户数据");
    }

    private void saveUsersToFile() {
        UserExcelUtil.saveUsersToFile(users);
        logger.log("保存用户数据到文件,共 " + users.size() + " 个用户");
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}