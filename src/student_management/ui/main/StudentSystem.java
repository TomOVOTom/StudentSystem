package student_management.ui.main;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.util.excel.ExcelFileManager;

import javax.swing.*;
import java.awt.*;

public class StudentSystem extends JFrame {
    private StudentClient studentClient;
    private User user;
    private TabManager tabManager;
    private DisplayUpdater displayUpdater;

    public StudentSystem(User user) {
        System.out.println("创建 StudentSystem 实例，用户：" + user.getUsername());
        try {
            ExcelFileManager.ensureExcelFilesExist();
            this.studentClient = new StudentClient("localhost", 12345);
            this.user = user;
            initComponents();
            this.displayUpdater = new DisplayUpdater(this.studentClient, this.user);
        } catch (Exception e) {
            System.err.println("初始化 StudentSystem 失败: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "系统初始化失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("StudentSystem 初始化失败", e);
        }
    }

    private void initComponents() {
        System.out.println("初始化组件");
        setTitle("学生管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabManager = new TabManager(studentClient, this, user);
        add(tabManager.getTabbedPane(), BorderLayout.CENTER);

        System.out.println("组件初始化完成");
    }

    public void updateDisplay() {
        System.out.println("更新显示");
        if (!checkServerConnection()) {
            JOptionPane.showMessageDialog(this, "无法连接到服务器，请检查服务器是否运行。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedIndex = tabManager.getSelectedIndex();
        String tabTitle = tabManager.getSelectedTabTitle();

        try {
            String result = displayUpdater.getUpdateResult(tabTitle);
//            System.out.println("查询结果：" + result);
            if (result != null && !result.startsWith("错误:")) {
                updateTabDisplay(selectedIndex, result);
            } else {
                JOptionPane.showMessageDialog(this, "获取数据失败: " + result, "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println("更新显示时出错: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "无法获取信息: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkServerConnection() {
        for (int i = 0; i < 3; i++) {
            try {
                studentClient.sendCommand("PING", user);
                return true;
            } catch (Exception e) {
                System.err.println("连接服务器失败 (尝试 " + (i + 1) + "/3): " + e.getMessage());
            }
        }
        return false;
    }

    public User getUser() {
        return this.user;
    }


    private void updateTabDisplay(int tabIndex, String result) {
        tabManager.updateTabDisplay(tabIndex, result);
    }
}