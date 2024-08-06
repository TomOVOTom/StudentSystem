package student_management.ui.components.usercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;
import student_management.util.validator.UserValidator;

import javax.swing.*;

public class UserButtonHandler {
    private StudentClient studentClient;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private StudentSystem studentSystem;
    private User currentUser;

    public UserButtonHandler(StudentClient studentClient, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, StudentSystem studentSystem, User currentUser) {
        this.studentClient = studentClient;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.roleComboBox = roleComboBox;
        this.studentSystem = studentSystem;
        this.currentUser = currentUser;
    }

   public JButton createAddUserButton() {
    JButton addButton = new JButton("添加用户");
    addButton.addActionListener(e -> {
        try {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            System.out.println("尝试添加用户: " + username + ", 角色: " + role);

            UserValidator.validateUsername(username);
            UserValidator.validatePassword(password);
            UserValidator.validateRole(role);

            User newUser = new User(username, password, role);
            String response = studentClient.sendCommand("USER_ADD", currentUser, newUser);
            System.out.println("服务器响应: " + response);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        } catch (IllegalArgumentException ex) {
            System.out.println("添加用户失败: " + ex.getMessage());
            JOptionPane.showMessageDialog(studentSystem, ex.getMessage(), "输入错误", JOptionPane.ERROR_MESSAGE);
        }
    });
    return addButton;
}

    public JButton createRemoveUserButton() {
        JButton removeButton = new JButton("删除用户");
        removeButton.addActionListener(e -> {
            String username = usernameField.getText();
            String response = studentClient.sendCommand("USER_REMOVE", currentUser, username);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateUserButton() {
        JButton updateButton = new JButton("更新用户");
        updateButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                UserValidator.validateUsername(username);
                UserValidator.validatePassword(password);
                UserValidator.validateRole(role);

                User updatedUser = new User(username, password, role);
                String response = studentClient.sendCommand("USER_UPDATE", currentUser, updatedUser);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(studentSystem, ex.getMessage(), "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return updateButton;
    }

    public JButton createQueryUserButton() {
        JButton queryButton = new JButton("查询用户");
        queryButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(studentSystem, "请输入要查询的用户名", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String response = studentClient.sendCommand("USER_QUERY", currentUser, username);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }


    public JButton createClearUserFieldsButton() {
        JButton clearButton = new JButton("清空用户输入框");
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            roleComboBox.setSelectedIndex(0);
        });
        return clearButton;
    }
}