package student_management.ui.components.studentcoursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class StudentCourseButtonHandler {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private StudentSystem studentSystem;
    private User user;

    public StudentCourseButtonHandler(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createSelectCourseButton() {
        JButton selectCourseButton = new JButton("选课");
        selectCourseButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("STUDENT_SELECT_COURSE", user, studentId, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return selectCourseButton;
    }

    public JButton createDropCourseButton() {
        JButton dropCourseButton = new JButton("退课");
        dropCourseButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("STUDENT_DROP_COURSE", user, studentId, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return dropCourseButton;
    }

    public JButton createQueryStudentCoursesButton() {
        JButton queryCoursesButton = new JButton("查询学生课程");
        queryCoursesButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String response = studentClient.sendCommand("STUDENT_QUERY_COURSES", user, studentId);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryCoursesButton;
    }

    public JButton createClearFieldsButton() {
        JButton clearFieldsButton = new JButton("清空字段");
        clearFieldsButton.addActionListener(e -> {
            studentIdField.setText("");
            courseIdField.setText("");
        });
        return clearFieldsButton;
    }
}