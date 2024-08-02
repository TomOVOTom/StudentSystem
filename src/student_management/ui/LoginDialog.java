package student_management.ui;

import student_management.model.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private User user;

    public LoginDialog(Frame parent) {
        super(parent, "登录", true);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("用户名:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("登录");
        add(loginButton);

       loginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // 这里可以添加用户验证逻辑
        user = new User(username, password, "admin"); // 示例用户，实际应从数据库或其他存储中验证
        dispose();
    }
});

        setSize(300, 150);
        setLocationRelativeTo(null);
    }


    public User getUser() {
        return user;
    }
}