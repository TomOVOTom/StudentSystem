package student_management.ui.button.course;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;

public class CourseButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public CourseButtonHandler(StudentClient studentClient, JTextField idField, JTextField courseIdField, JTextField courseNameField, JTextField teacherField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddCourseButton() {
        return new AddCourseButton(studentClient, idField, courseIdField, courseNameField, teacherField, gradeField, studentSystem).createButton();
    }

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentClient, idField, courseIdField, studentSystem).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentClient, idField, courseIdField, courseNameField, teacherField, gradeField, studentSystem).createButton();
    }

    public JButton createQueryCourseButton() {
        return new QueryCourseButton(studentClient, idField, courseIdField, studentSystem).createButton();
    }

    public JButton createClearCourseFieldsButton() {
        JButton clearButton = new JButton("清空课程输入框");
        clearButton.addActionListener(e -> {
            courseIdField.setText("");
            courseNameField.setText("");
            teacherField.setText("");
            gradeField.setText("");
        });
        return clearButton;
    }
}