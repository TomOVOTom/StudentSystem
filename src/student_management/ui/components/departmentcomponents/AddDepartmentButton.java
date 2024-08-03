package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Department;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

    public AddDepartmentButton(StudentClient studentClient, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton addDepartmentButton = new JButton("添加院系");
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String departmentName = departmentNameField.getText();
                Department newDepartment = new Department(departmentId, departmentName);
                String response = studentClient.sendCommand("DEPARTMENT_ADD_DEPARTMENT", user, newDepartment);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addDepartmentButton;
    }
}