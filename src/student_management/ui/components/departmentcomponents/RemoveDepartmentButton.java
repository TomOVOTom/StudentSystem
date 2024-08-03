package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;
    private User user;

    public RemoveDepartmentButton(StudentClient studentClient, JTextField departmentIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton removeDepartmentButton = new JButton("删除院系");
        removeDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String response = studentClient.sendCommand("DEPARTMENT_REMOVE_DEPARTMENT", user, departmentId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeDepartmentButton;
    }
}