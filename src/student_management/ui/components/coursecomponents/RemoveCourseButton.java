package student_management.ui.components.coursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCourseButton {
    private StudentClient studentClient;
    private JTextField courseIdField;
    private StudentSystem studentSystem;
    private User user;

    public RemoveCourseButton(StudentClient studentClient, JTextField courseIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.courseIdField = courseIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton removeCourseButton = new JButton("删除课程");
        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId = courseIdField.getText();
                String response = studentClient.sendCommand("COURSE_REMOVE_COURSE", user, courseId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeCourseButton;
    }
}