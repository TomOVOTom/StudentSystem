package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateGradeButton {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;
    private StudentSystem studentSystem;
    private User user;

    public UpdateGradeButton(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, JTextField scoreField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.scoreField = scoreField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton updateGradeButton = new JButton("更新成绩");
        updateGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                String score = scoreField.getText();
                String response = studentClient.sendCommand("GRADE_UPDATE_GRADE", user, studentId, courseId, score);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateGradeButton;
    }
}