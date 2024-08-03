package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Grade;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGradeButton {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;
    private StudentSystem studentSystem;
    private User user;

    public AddGradeButton(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, JTextField scoreField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.scoreField = scoreField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton addGradeButton = new JButton("添加成绩");
        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                String scoreStr = scoreField.getText();
                try {
                    int score = Integer.parseInt(scoreStr);
                    Grade newGrade = new Grade(studentId, courseId, score);
                    String response = studentClient.sendCommand("GRADE_ADD_GRADE", user, newGrade);
                    JOptionPane.showMessageDialog(studentSystem, response);
                    studentSystem.updateDisplay();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(studentSystem, "成绩必须是一个整数", "输入错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return addGradeButton;
    }
}