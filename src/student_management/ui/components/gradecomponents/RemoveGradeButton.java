package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveGradeButton {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private StudentSystem studentSystem;
    private User user;

    public RemoveGradeButton(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton removeGradeButton = new JButton("删除成绩");
        removeGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                String response = studentClient.sendCommand("GRADE_REMOVE_GRADE", user, studentId, courseId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeGradeButton;
    }
}