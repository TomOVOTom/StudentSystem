package student_management.ui.core;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.DisplayUpdater;
import student_management.ui.main.StudentSystem;
import student_management.util.excel.ExcelFileManager;

import javax.swing.*;

public class SystemInitializer {
    public static void initialize(StudentSystem studentSystem, User user) {
        try {
            ExcelFileManager.ensureExcelFilesExist();
            System.out.println("Excel 文件初始化完成");
            StudentClient studentClient = new StudentClient("localhost", 12345);
            studentSystem.setStudentClient(studentClient);
            studentSystem.setUser(user);
            studentSystem.initComponents();
            studentSystem.setDisplayUpdater(new DisplayUpdater(studentClient, user));
        } catch (Exception e) {
            System.err.println("初始化 StudentSystem 失败: " + e.getMessage());
            JOptionPane.showMessageDialog(studentSystem, "系统初始化失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("StudentSystem 初始化失败", e);
        }
    }
}