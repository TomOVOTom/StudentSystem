package student_management.util.excelutil.user;

import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.commonutil.LoggerUtil;

import java.util.HashMap;

public class UserExcelVerifier {
    private static final Logger logger = LoggerUtil.getInstance();

    public static void verifyUsersSaved(String fileName, HashMap<String, User> originalUsers) {
        logger.log("开始验证保存的用户数据");
        HashMap<String, User> reloadedUsers = student_management.util.excelutil.user.UserExcelLoader.loadUsersFromFile(fileName);
        logger.log("重新加载的用户数: " + reloadedUsers.size());
        logger.log("原始用户数: " + originalUsers.size());

        if (reloadedUsers.size() != originalUsers.size()) {
            logger.log("警告：保存的用户数量与原始用户数量不匹配");
        }

        for (User originalUser : originalUsers.values()) {
            User reloadedUser = reloadedUsers.get(originalUser.getUsername());
            if (reloadedUser == null) {
                logger.log("错误：未找到用户 " + originalUser.getUsername());
            } else if (!reloadedUser.equals(originalUser)) {
                logger.log("错误：用户数据不匹配 " + originalUser.getUsername());
                logger.log("原始数据: " + originalUser);
                logger.log("重新加载的数据: " + reloadedUser);
            }
        }

        logger.log("用户数据验证完成");
    }
}