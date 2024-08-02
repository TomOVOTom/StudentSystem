package student_management.ui.button.department;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;

public class DepartmentButtonHandler {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;

    public DepartmentButtonHandler(StudentClient studentClient, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddDepartmentButton() {
        return new AddDepartmentButton(studentClient, departmentIdField, departmentNameField, studentSystem).createButton();
    }

    public JButton createRemoveDepartmentButton() {
        return new RemoveDepartmentButton(studentClient, departmentIdField, studentSystem).createButton();
    }

    public JButton createUpdateDepartmentButton() {
        return new UpdateDepartmentButton(studentClient, departmentIdField, departmentNameField, studentSystem).createButton();
    }

    public JButton createQueryDepartmentButton() {
        return new QueryDepartmentButton(studentClient, departmentIdField, studentSystem).createButton();
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