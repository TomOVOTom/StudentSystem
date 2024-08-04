package student_management.ui.components.coursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class CourseButtonHandler {
    private StudentClient studentClient;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JTextField creditsField;
    private JComboBox<String> gradingSystemComboBox;
    private StudentSystem studentSystem;
    private User user;

    public CourseButtonHandler(StudentClient studentClient, JTextField courseIdField,
                               JTextField courseNameField, JTextField teacherField,
                               JTextField creditsField,
                               JComboBox<String> gradingSystemComboBox,
                               StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.creditsField = creditsField;
        this.gradingSystemComboBox = gradingSystemComboBox;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddCourseButton() {
    return new AddCourseButton(studentClient, courseIdField, courseNameField, teacherField, creditsField, gradingSystemComboBox, studentSystem, user).createButton();
}

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentClient, courseIdField, studentSystem, user).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentClient, courseIdField, courseNameField, teacherField, gradingSystemComboBox, studentSystem, user).createButton();
    }

    public JButton createQueryCourseButton() {
        return new QueryCourseButton(studentClient, courseIdField, studentSystem, user).createButton();
    }

    public JButton createClearCourseFieldsButton() {
        JButton clearButton = new JButton("清空课程输入框");
        clearButton.addActionListener(e -> {
            courseIdField.setText("");
            courseNameField.setText("");
            teacherField.setText("");
            gradingSystemComboBox.setSelectedIndex(0);
        });
        return clearButton;
    }
}