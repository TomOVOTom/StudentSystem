package student_management.ui.button.department;

import student_management.client.StudentClient;
import student_management.model.Department;
import student_management.ui.StudentSystem;
import student_management.util.LoggerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;

    public AddDepartmentButton(StudentClient studentClient, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addDepartmentButton = new JButton("添加院系");
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String departmentName = departmentNameField.getText();
                Department newDepartment = new Department(departmentId, departmentName);
                String response = studentClient.sendCommand("DEPARTMENT_ADD_DEPARTMENT", newDepartment);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
                LoggerUtil.log("添加院系: " + newDepartment.toString());
            }
        });
        return addDepartmentButton;
    }
}