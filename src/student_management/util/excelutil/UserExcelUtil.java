package student_management.util.excelutil;

import student_management.model.entity.User;
import student_management.util.excelutil.user.UserExcelSaver;
import student_management.util.excelutil.user.UserExcelLoader;
import student_management.util.excelutil.user.UserExcelVerifier;

import java.io.File;
import java.util.HashMap;

public class UserExcelUtil {
    private static final String USER_FILE_NAME = "users.xlsx";

    public static void saveUsersToFile(HashMap<String, User> users) {
        UserExcelSaver.saveUsersToFile(USER_FILE_NAME, users);
        UserExcelVerifier.verifyUsersSaved(USER_FILE_NAME, users);
    }

    public static HashMap<String, User> loadUsersFromFile() {
    String fullPath = getUserFileName();
    return UserExcelLoader.loadUsersFromFile(fullPath);
}

   public static String getUserFileName() {
    String projectRoot = System.getProperty("user.dir");
    if (projectRoot.endsWith("src")) {
        projectRoot = new File(projectRoot).getParent();
    }
    String fileName = "users.xlsx";
    String fullPath = new File(projectRoot, fileName).getAbsolutePath();
    System.out.println("尝试加载用户文件: " + fullPath);
    return fullPath;
}

public static String getProjectRootPath() {
    String currentPath = System.getProperty("user.dir");
    if (currentPath.endsWith("src")) {
        return new File(currentPath).getParent();
    }
    return currentPath;
}
}