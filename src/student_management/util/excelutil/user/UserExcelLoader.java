package student_management.util.excelutil.user;

import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.commonutil.LoggerUtil;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;

import java.io.File;
import java.util.HashMap;

public class UserExcelLoader {
    private static final Logger logger = LoggerUtil.getInstance();

   public static HashMap<String, User> loadUsersFromFile(String fileName) {
    String absolutePath = new File(fileName).getAbsolutePath();
    logger.log("开始从文件加载用户数据: " + absolutePath);
    logger.log("文件绝对路径: " + absolutePath);
    File file = new File(absolutePath);
    if (!file.exists()) {
        logger.log("用户文件不存在: " + absolutePath);
        return new HashMap<>();
    }
    if (!file.canRead()) {
        logger.log("无法读取用户文件: " + absolutePath);
        return new HashMap<>();
    }
    logger.log("用户文件存在,开始读取数据");
    HashMap<String, User> users = (HashMap<String, User>) ExcelFileLoader.loadFromFile(absolutePath, row -> {
        String username = CellUtil.getCellValueAsString(row.getCell(0));
        String password = CellUtil.getCellValueAsString(row.getCell(1));
        String role = CellUtil.getCellValueAsString(row.getCell(2));
        logger.log("读取到用户数据: " + username + ", " + role);
        return new User(username, password, role);
    }, User::getUsername);
    logger.log("从文件加载了 " + users.size() + " 条用户记录");
    return users;
}
}