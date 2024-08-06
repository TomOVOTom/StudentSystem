package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Department;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class DepartmentButtonHandler {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

    public DepartmentButtonHandler(StudentClient studentClient, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddDepartmentButton() {
        JButton addButton = new JButton("添加院系");
        addButton.addActionListener(e -> {
            String departmentId = departmentIdField.getText();
            String departmentName = departmentNameField.getText();
            Department newDepartment = new Department(departmentId, departmentName);
            String response = studentClient.sendCommand("DEPARTMENT_ADD_DEPARTMENT", user, newDepartment);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return addButton;
    }

    public JButton createRemoveDepartmentButton() {
        JButton removeButton = new JButton("删除院系");
        removeButton.addActionListener(e -> {
            String departmentId = departmentIdField.getText();
            String response = studentClient.sendCommand("DEPARTMENT_REMOVE_DEPARTMENT", user, departmentId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateDepartmentButton() {
        JButton updateButton = new JButton("更新院系");
        updateButton.addActionListener(e -> {
            String departmentId = departmentIdField.getText();
            String departmentName = departmentNameField.getText();
            Department updatedDepartment = new Department(departmentId, departmentName);
            String response = studentClient.sendCommand("DEPARTMENT_UPDATE_DEPARTMENT", user, updatedDepartment);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return updateButton;
    }

    public JButton createQueryDepartmentButton() {
        JButton queryButton = new JButton("查询院系");
        queryButton.addActionListener(e -> {
            String departmentId = departmentIdField.getText();
            String response = studentClient.sendCommand("DEPARTMENT_QUERY_DEPARTMENT", user, departmentId);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }

    public JButton createClearDepartmentFieldsButton() {
        JButton clearButton = new JButton("清空院系输入框");
        clearButton.addActionListener(e -> {
            departmentIdField.setText("");
            departmentNameField.setText("");
        });
        return clearButton;
    }
}