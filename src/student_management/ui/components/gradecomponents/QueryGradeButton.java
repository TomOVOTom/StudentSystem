package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryGradeButton {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private StudentSystem studentSystem;
    private User user;

    public QueryGradeButton(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton queryGradeButton = new JButton("查询成绩");
        queryGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                String response = studentClient.sendCommand("GRADE_QUERY_GRADE", user, studentId, courseId);
                JOptionPane.showMessageDialog(studentSystem, response);
            }
        });
        return queryGradeButton;
    }
}