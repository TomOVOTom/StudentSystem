package student_management.ui.button.student_class;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;

public class StudentClassButtonHandler {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;

    public StudentClassButtonHandler(StudentClient studentClient, JTextField classIdField, JTextField classNameField, JTextField departmentField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddStudentClassButton() {
        return new AddStudentClassButton(studentClient, classIdField, classNameField, departmentField, studentSystem).createButton();
    }

    public JButton createRemoveStudentClassButton() {
        return new RemoveStudentClassButton(studentClient, classIdField, studentSystem).createButton();
    }

    public JButton createUpdateStudentClassButton() {
        return new UpdateStudentClassButton(studentClient, classIdField, classNameField, departmentField, studentSystem).createButton();
    }

    public JButton createQueryStudentClassButton() {
        return new QueryStudentClassButton(studentClient, classIdField, studentSystem).createButton();
    }

    public JButton createClearStudentClassFieldsButton() {
        JButton clearButton = new JButton("清空班级输入框");
        clearButton.addActionListener(e -> {
            classIdField.setText("");
            classNameField.setText("");
            departmentField.setText("");
        });
        return clearButton;
    }
}