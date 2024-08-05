package student_management.ui.components.studentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class StudentButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

    public StudentButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField genderField, JTextField classIdField, JTextField classNameField, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.genderField = genderField;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddStudentButton() {
        return new AddStudentButton(studentClient, idField, nameField, ageField, genderField, classIdField, classNameField, departmentIdField, departmentNameField, studentSystem, user).createButton();
    }

    public JButton createRemoveStudentButton() {
        return new RemoveStudentButton(studentClient, idField, studentSystem, user).createButton();
    }

    public JButton createUpdateStudentButton() {
        return new UpdateStudentButton(studentClient, idField, nameField, ageField, genderField, classIdField, classNameField, departmentIdField, departmentNameField, studentSystem, user).createButton();
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
            genderField.setText("");
            classIdField.setText("");
            classNameField.setText("");
            departmentIdField.setText("");
            departmentNameField.setText("");
        });
        return clearButton;
    }
}