package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class GradeButtonHandler {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;
    private StudentSystem studentSystem;
    private User user;

    public GradeButtonHandler(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, JTextField scoreField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.scoreField = scoreField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddGradeButton() {
        return new AddGradeButton(studentClient, studentIdField, courseIdField, scoreField, studentSystem, user).createButton();
    }

    public JButton createRemoveGradeButton() {
        return new RemoveGradeButton(studentClient, studentIdField, courseIdField, studentSystem, user).createButton();
    }

    public JButton createUpdateGradeButton() {
        return new UpdateGradeButton(studentClient, studentIdField, courseIdField, scoreField, studentSystem, user).createButton();
    }

    public JButton createQueryGradeButton() {
        return new QueryGradeButton(studentClient, studentIdField, courseIdField, studentSystem, user).createButton();
    }

    public JButton createClearGradeFieldsButton() {
        JButton clearButton = new JButton("清空成绩输入框");
        clearButton.addActionListener(e -> {
            studentIdField.setText("");
            courseIdField.setText("");
            scoreField.setText("");
        });
        return clearButton;
    }
}