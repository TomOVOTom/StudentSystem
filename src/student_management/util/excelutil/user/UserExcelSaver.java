package student_management.util.excelutil.user;

import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.commonutil.LoggerUtil;
import student_management.util.excel.ExcelFileSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserExcelSaver {
    private static final Logger logger = LoggerUtil.getInstance();

    public static void saveUsersToFile(String fileName, HashMap<String, User> users) {
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        logger.log("开始保存用户数据到Excel文件: " + absolutePath);
        String[] headers = {"用户名", "密码", "角色"};
        List<User> userList = new ArrayList<>(users.values());
        try {
            logger.log("文件路径: " + absolutePath);
            logger.log("文件是否存在: " + file.exists());
            logger.log("文件是否可写: " + file.canWrite());

            ExcelFileSaver.saveToFile(absolutePath, userList, headers, (row, user) -> {
                row.createCell(0).setCellValue(user.getUsername());
                row.createCell(1).setCellValue(user.getPassword());
                row.createCell(2).setCellValue(user.getRole());
            });
            logger.log("用户数据保存成功,共保存 " + users.size() + " 条记录");
        } catch (Exception e) {
            logger.log("保存用户数据时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}