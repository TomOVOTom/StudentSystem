package student_management.ui.components.usercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserButton {
    private StudentClient studentClient;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private StudentSystem studentSystem;
    private User currentUser;

    public UpdateUserButton(StudentClient studentClient, JTextField usernameField, JPasswordField passwordField, JTextField roleField, StudentSystem studentSystem, User currentUser) {
        this.studentClient = studentClient;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.roleField = roleField;
        this.studentSystem = studentSystem;
        this.currentUser = currentUser;
    }

    public JButton createButton() {
        JButton updateUserButton = new JButton("更新用户");
        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleField.getText();
                String response = studentClient.sendCommand("USER_UPDATE_USER", currentUser, username, password, role);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateUserButton;
    }
}