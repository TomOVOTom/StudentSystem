package student_management.ui.components.usercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class UserButtonHandler {
    private StudentClient studentClient;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private StudentSystem studentSystem;
    private User currentUser;

    public UserButtonHandler(StudentClient studentClient, JTextField usernameField, JPasswordField passwordField, JTextField roleField, StudentSystem studentSystem, User currentUser) {
        this.studentClient = studentClient;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.roleField = roleField;
        this.studentSystem = studentSystem;
        this.currentUser = currentUser;
    }

    public JButton createAddUserButton() {
        return new AddUserButton(studentClient, usernameField, passwordField, roleField, studentSystem, currentUser).createButton();
    }

    public JButton createRemoveUserButton() {
        return new RemoveUserButton(studentClient, usernameField, studentSystem, currentUser).createButton();
    }

    public JButton createUpdateUserButton() {
        return new UpdateUserButton(studentClient, usernameField, passwordField, roleField, studentSystem, currentUser).createButton();
    }

    public JButton createQueryUserButton() {
        return new QueryUserButton(studentClient, usernameField, studentSystem, currentUser).createButton();
    }

    public JButton createClearUserFieldsButton() {
        JButton clearButton = new JButton("清空用户输入框");
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            roleField.setText("");
        });
        return clearButton;
    }
}