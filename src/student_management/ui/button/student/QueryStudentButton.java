package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private StudentSystem studentSystem;

    public QueryStudentButton(StudentClient studentClient, JTextField idField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton queryStudentButton = new JButton("查询学生");
        queryStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String result = studentClient.sendCommand("QUERY_STUDENT", id);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryStudentButton;
    }
}