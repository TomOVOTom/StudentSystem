package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.StudentSystem;

import javax.swing.*;

public class StudentButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField classIdField;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;
    private User user;

    public StudentButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField classIdField, JTextField departmentIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.classIdField = classIdField;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddStudentButton() {
        return new AddStudentButton(studentClient, idField, nameField, ageField, classIdField, departmentIdField, studentSystem, user).createButton();
    }

    public JButton createRemoveStudentButton() {
        return new RemoveStudentButton(studentClient, idField, studentSystem, user).createButton();
    }

    public JButton createUpdateStudentButton() {
        return new UpdateStudentButton(studentClient, idField, nameField, ageField, studentSystem, user).createButton();
    }

    public JButton createQueryStudentButton() {
        return new QueryStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createClearStudentFieldsButton() {
        JButton clearButton = new JButton("清空学生输入框");
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            classIdField.setText("");
            departmentIdField.setText("");
        });
        return clearButton;
    }
}