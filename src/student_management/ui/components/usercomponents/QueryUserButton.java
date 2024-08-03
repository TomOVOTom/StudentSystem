package student_management.ui.components.usercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryUserButton {
    private StudentClient studentClient;
    private JTextField usernameField;
    private StudentSystem studentSystem;
    private User currentUser;

    public QueryUserButton(StudentClient studentClient, JTextField usernameField, StudentSystem studentSystem, User currentUser) {
        this.studentClient = studentClient;
        this.usernameField = usernameField;
        this.studentSystem = studentSystem;
        this.currentUser = currentUser;
    }

    public JButton createButton() {
        JButton queryUserButton = new JButton("查询用户");
        queryUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String response = studentClient.sendCommand("USER_QUERY_USER", currentUser, username);
                JOptionPane.showMessageDialog(studentSystem, response);
            }
        });
        return queryUserButton;
    }
}