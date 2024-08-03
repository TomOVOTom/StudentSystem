package student_management.model.manager;

import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.UserExcelUtil;

import java.util.HashMap;
import java.util.Optional;

public class UserManager {
    private HashMap<String, User> users;
    private Logger logger;

    public UserManager(Logger logger) {
        this.logger = logger;
        users = new HashMap<>();
        loadUsersFromFile();
        logger.log("UserManager 初始化完成");
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        saveUsersToFile();
        logger.log("添加用户: " + user.getUsername());
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
        logger.log("保存用户数据到文件");
    }

    public void initializeDefaultUsers() {
        if (users.isEmpty()) {
            addUser(new User("admin", "admin123", "admin"));
            addUser(new User("user", "user123", "user"));
            logger.log("初始化默认用户");
        }
    }
}