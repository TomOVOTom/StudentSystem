package student_management.ui.components.coursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseButton {
    private StudentClient studentClient;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JComboBox<String> gradingSystemComboBox;
    private StudentSystem studentSystem;
    private User user;

    public UpdateCourseButton(StudentClient studentClient, JTextField courseIdField, JTextField courseNameField, JTextField teacherField, JComboBox<String> gradingSystemComboBox, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.gradingSystemComboBox = gradingSystemComboBox;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton updateCourseButton = new JButton("更新课程");
        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId = courseIdField.getText();
                String courseName = courseNameField.getText();
                String teacher = teacherField.getText();
                String gradingSystem = (String) gradingSystemComboBox.getSelectedItem();
                String response = studentClient.sendCommand("COURSE_UPDATE_COURSE", user, courseId, courseName, teacher, gradingSystem);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateCourseButton;
    }
}