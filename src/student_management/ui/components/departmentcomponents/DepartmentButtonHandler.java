package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
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
        return new AddDepartmentButton(studentClient, departmentIdField, departmentNameField, studentSystem, user).createButton();
    }

    public JButton createRemoveDepartmentButton() {
        return new RemoveDepartmentButton(studentClient, departmentIdField, studentSystem, user).createButton();
    }

    public JButton createUpdateDepartmentButton() {
        return new UpdateDepartmentButton(studentClient, departmentIdField, departmentNameField, studentSystem, user).createButton();
    }

    public JButton createQueryDepartmentButton() {
        return new QueryDepartmentButton(studentClient, departmentIdField, studentSystem, user).createButton();
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