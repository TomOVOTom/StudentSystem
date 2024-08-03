package student_management.ui.components.teachercomponents;

import student_management.client.StudentClient;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class TeacherButtonHandler {
    private StudentClient studentClient;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;
    private StudentSystem studentSystem;

    public TeacherButtonHandler(StudentClient studentClient, JTextField teacherIdField, JTextField teacherNameField, JTextField teacherSubjectField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.teacherIdField = teacherIdField;
        this.teacherNameField = teacherNameField;
        this.teacherSubjectField = teacherSubjectField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddTeacherButton() {
        return new AddTeacherButton(studentClient, teacherIdField, teacherNameField, teacherSubjectField, studentSystem).createButton();
    }

    public JButton createRemoveTeacherButton() {
        return new RemoveTeacherButton(studentClient, teacherIdField, studentSystem).createButton();
    }

    public JButton createUpdateTeacherButton() {
        return new UpdateTeacherButton(studentClient, teacherIdField, teacherNameField, teacherSubjectField, studentSystem).createButton();
    }

    public JButton createQueryTeacherButton() {
        return new QueryTeacherButton(studentClient, teacherIdField, studentSystem).createButton();
    }

    public JButton createClearTeacherFieldsButton() {
        JButton clearButton = new JButton("清空老师输入框");
        clearButton.addActionListener(e -> {
            teacherIdField.setText("");
            teacherNameField.setText("");
            teacherSubjectField.setText("");
        });
        return clearButton;
    }
}