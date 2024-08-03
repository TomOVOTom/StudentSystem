package student_management.ui.components.coursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryCourseButton {
    private StudentClient studentClient;
    private JTextField courseIdField;
    private StudentSystem studentSystem;
    private User user;

    public QueryCourseButton(StudentClient studentClient, JTextField courseIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.courseIdField = courseIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton queryCourseButton = new JButton("查询课程");
        queryCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId = courseIdField.getText();
                String response = studentClient.sendCommand("STUDENT_QUERY_COURSE", user, courseId);
                JOptionPane.showMessageDialog(studentSystem, response);
            }
        });
        return queryCourseButton;
    }
}