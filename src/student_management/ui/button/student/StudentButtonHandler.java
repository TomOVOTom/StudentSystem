package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;

public class StudentButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private StudentSystem studentSystem;

    public StudentButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddStudentButton() {
        return new AddStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createRemoveStudentButton() {
        return new RemoveStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createUpdateStudentButton() {
        return new UpdateStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
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
        });
        return clearButton;
    }
}