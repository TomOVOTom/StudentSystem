package student_management.ui;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.model.manager.UserManager;
import student_management.ui.panel.ButtonPanel;
import student_management.ui.panel.InputPanel;

import javax.swing.*;
import java.awt.*;

public class StudentSystem extends JFrame {
    private StudentClient studentClient;
    private JTextArea displayArea;
    private User user;

    public StudentSystem(User user) {
        System.out.println("创建 StudentSystem 实例，用户：" + user.getUsername());
        this.studentClient = new StudentClient("localhost", 12345);
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        System.out.println("初始化组件");
        setTitle("学生管理系统");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        InputPanel inputPanel = new InputPanel();
        add(inputPanel.getPanel(), BorderLayout.NORTH);

        ButtonPanel buttonPanel = new ButtonPanel(studentClient, inputPanel, this, user);
        add(buttonPanel.getPanel(), BorderLayout.SOUTH);

        updateDisplay();
        System.out.println("组件初始化完成");
    }

    public void updateDisplay() {
        System.out.println("更新显示");
        try {
            String result = studentClient.sendCommand("STUDENT_QUERY_ALL_STUDENTS", user);
            System.out.println("查询结果：" + result);
            displayArea.setText(result);
        } catch (Exception e) {
            System.err.println("更新显示时出错: " + e.getMessage());
            displayArea.setText("无法获取学生信息，请检查服务器连接。");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UserManager userManager = new UserManager();
                userManager.initializeDefaultUsers();
                LoginDialog loginDialog = new LoginDialog(null);
                loginDialog.setVisible(true);
                User user = loginDialog.getUser();
                if (user != null) {
                    System.out.println("用户登录成功，创建 StudentSystem");
                    new StudentSystem(user).setVisible(true);
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

    public User getUser() {
        return this.user;
    }
}