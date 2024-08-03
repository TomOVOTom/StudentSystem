package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Department;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

    public UpdateDepartmentButton(StudentClient studentClient, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton updateDepartmentButton = new JButton("更新院系");
        updateDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String departmentName = departmentNameField.getText();
                Department updatedDepartment = new Department(departmentId, departmentName);
                String response = studentClient.sendCommand("DEPARTMENT_UPDATE_DEPARTMENT", user, updatedDepartment);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateDepartmentButton;
    }
}