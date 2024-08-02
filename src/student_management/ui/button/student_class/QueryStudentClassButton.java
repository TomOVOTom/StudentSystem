package student_management.ui.button.student_class;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryStudentClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private StudentSystem studentSystem;

    public QueryStudentClassButton(StudentClient studentClient, JTextField classIdField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton queryClassButton = new JButton("查询班级");
        queryClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String result = studentClient.sendCommand("CLASS_QUERY_CLASS", classId);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryClassButton;
    }
}