package student_management.model.manager;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import student_management.model.entity.User;
import student_management.util.excel.WorkbookUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private static final String USER_FILE = "data/users.xlsx";
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        createDataDirectoryIfNotExists();
        loadUsersFromFile();
    }

    private void createDataDirectoryIfNotExists() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    public void loadUsersFromFile() {
    File file = new File(USER_FILE);
    if (!file.exists()) {
        System.out.println("用户文件不存在，将在保存时创建。");
        return;
    }
    try (FileInputStream fileIn = new FileInputStream(file);
         Workbook workbook = WorkbookUtil.loadWorkbook(fileIn)) {
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            String role = row.getCell(2).getStringCellValue();
            users.add(new User(username, password, role));
        }
    } catch (IOException e) {
        System.err.println("加载用户文件时出错：" + e.getMessage());
    }
}

   public void saveUsersToFile() {
    File file = new File(USER_FILE);
    file.getParentFile().mkdirs(); // 确保父目录存在
    try (Workbook workbook = new XSSFWorkbook();
         FileOutputStream fileOut = new FileOutputStream(file)) {
        Sheet sheet = workbook.createSheet("Users");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        headerRow.createCell(1).setCellValue("Password");
        headerRow.createCell(2).setCellValue("Role");

        int rowNum = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getUsername());
            row.createCell(1).setCellValue(user.getPassword());
            row.createCell(2).setCellValue(user.getRole());
        }
        WorkbookUtil.saveWorkbook(workbook, fileOut);
        System.out.println("用户数据已保存到文件。");
    } catch (IOException e) {
        System.err.println("保存用户文件时出错：" + e.getMessage());
    }
}

    public Optional<User> authenticateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    // 其他方法如更新用户、删除用户等可以根据需要添加
    public void initializeDefaultUsers() {
        if (users.isEmpty()) {
            addUser(new User("admin", "admin123", "admin"));
            addUser(new User("user", "user123", "user"));
            saveUsersToFile();
        }
    }
}