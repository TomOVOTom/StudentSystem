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
        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.addActionListener(e -> {
            String courseId = courseIdField.getText();
            String courseName = courseNameField.getText();
            String teacherId = teacherField.getText();
            String gradingSystem = (String) gradingSystemComboBox.getSelectedItem();
            String credits = creditsField.getText();
            String response = studentClient.sendCommand("COURSE_ADD", user, courseId, courseName, gradingSystem, teacherId, credits);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return addCourseButton;
    }

    public JButton createRemoveCourseButton() {
        JButton removeCourseButton = new JButton("删除课程");
        removeCourseButton.addActionListener(e -> {
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("COURSE_REMOVE", user, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeCourseButton;
    }

    public JButton createUpdateCourseButton() {
        JButton updateCourseButton = new JButton("更新课程");
        updateCourseButton.addActionListener(e -> {
            String courseId = courseIdField.getText();
            String courseName = courseNameField.getText();
            String teacher = teacherField.getText();
            String gradingSystem = (String) gradingSystemComboBox.getSelectedItem();
            String credits = creditsField.getText();
            String response = studentClient.sendCommand("COURSE_UPDATE", user, courseId, courseName, teacher, gradingSystem, credits);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return updateCourseButton;
    }

    public JButton createQueryCourseButton() {
        JButton queryCourseButton = new JButton("查询课程");
        queryCourseButton.addActionListener(e -> {
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("COURSE_QUERY", user, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryCourseButton;
    }

    public JButton createClearCourseFieldsButton() {
        JButton clearButton = new JButton("清空课程输入框");
        clearButton.addActionListener(e -> {
            courseIdField.setText("");
            courseNameField.setText("");
            teacherField.setText("");
            creditsField.setText("");
            gradingSystemComboBox.setSelectedIndex(0);
        });
        return clearButton;
    }
}