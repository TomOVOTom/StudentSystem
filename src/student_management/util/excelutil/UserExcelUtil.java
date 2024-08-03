package student_management.util.excelutil;

import student_management.model.entity.User;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserExcelUtil {
    private static final String USER_FILE_NAME = "users.xlsx";

    public static void saveUsersToFile(HashMap<String, User> users) {
        String[] headers = {"用户名", "密码", "角色"};
        List<User> userList = new ArrayList<>(users.values());
        ExcelFileSaver.saveToFile(USER_FILE_NAME, userList, headers, (row, user) -> {
            row.createCell(0).setCellValue(user.getUsername());
            row.createCell(1).setCellValue(user.getPassword());
            row.createCell(2).setCellValue(user.getRole());
        });
    }

    public static HashMap<String, User> loadUsersFromFile() {
        return (HashMap<String, User>) ExcelFileLoader.loadFromFile(USER_FILE_NAME, row -> {
            String username = CellUtil.getCellValueAsString(row.getCell(0));
            String password = CellUtil.getCellValueAsString(row.getCell(1));
            String role = CellUtil.getCellValueAsString(row.getCell(2));
            return new User(username, password, role);
        }, User::getUsername);
    }
}