package student_management.ui.components.classcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private StudentSystem studentSystem;
    private User user;

    public QueryClassButton(StudentClient studentClient, JTextField classIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton queryClassButton = new JButton("查询班级");
        queryClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String result = studentClient.sendCommand("CLASS_QUERY_CLASS", user, classId);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryClassButton;
    }
}