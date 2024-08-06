package student_management.ui.main;

import student_management.model.entity.User;
import student_management.model.manager.UserManager;
import student_management.util.commonutil.ConsoleLogger;
import student_management.util.commonutil.Logger;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        SwingUtilities.invokeLater(() -> {
            try {
                Logger logger = new ConsoleLogger();
                UserManager userManager = new UserManager(logger);
                // 移除了 userManager.initializeDefaultUsers() 调用
                LoginDialog loginDialog = new LoginDialog(null, userManager);
                loginDialog.setVisible(true);
                User user = loginDialog.getUser();
                if (user != null) {
                    System.out.println("用户登录成功，创建 StudentSystem");
                    StudentSystem system = new StudentSystem(user);
                    system.setVisible(true);
                } else {
                    System.out.println("用户登录失败，退出程序");
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "程序初始化失败：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}