package student_management.ui;

import student_management.model.entity.User;
import student_management.model.manager.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private User user;
    private UserManager userManager;

    public LoginDialog(Frame parent) {
        super(parent, "登录", true);
        this.userManager = new UserManager();
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
                Optional<User> authenticatedUser = userManager.authenticateUser(username, password);
                if (authenticatedUser.isPresent()) {
                    user = authenticatedUser.get();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public User getUser() {
        return user;
    }
}