package student_management.ui.core;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.DisplayUpdater;
import student_management.ui.main.StudentSystem;
import student_management.ui.main.TabManager;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DisplayManager {
    private StudentSystem studentSystem;
    private StudentClient studentClient;
    private User user;
    private DisplayUpdater displayUpdater;

    public DisplayManager(StudentSystem studentSystem, StudentClient studentClient, User user) {
        this.studentSystem = studentSystem;
        this.studentClient = studentClient;
        this.user = user;
        this.displayUpdater = new DisplayUpdater(studentClient, user);
    }

    public void updateDisplay(TabManager tabManager) {
        System.out.println("更新显示");
        if (!checkServerConnection()) {
            JOptionPane.showMessageDialog(studentSystem, "无法连接到服务器，请检查服务器是否运行。", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedIndex = tabManager.getSelectedIndex();
        String tabTitle = tabManager.getSelectedTabTitle();

        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                return displayUpdater.getUpdateResult(tabTitle);
            });

            String result = future.get(10, TimeUnit.SECONDS); // 设置10秒超时
            System.out.println("收到服务器响应: " + result);
            if (result != null && !result.startsWith("错误:")) {
                tabManager.updateTabDisplay(selectedIndex, result);
            } else {
                JOptionPane.showMessageDialog(studentSystem, "获取数据失败: " + result, "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (TimeoutException e) {
            System.err.println("更新显示超时: " + e.getMessage());
            JOptionPane.showMessageDialog(studentSystem, "操作超时，请稍后重试", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("更新显示时出错: " + e.getMessage());
            JOptionPane.showMessageDialog(studentSystem, "无法获取信息: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
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
}