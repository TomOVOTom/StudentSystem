package student_management.ui.components.studentcomponents;

import student_management.client.StudentClient;
import student_management.ui.main.StudentSystem;

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
        JButton queryButton = new JButton("查询学生");
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String response = studentClient.sendCommand("STUDENT_QUERY_STUDENT", id);
                JOptionPane.showMessageDialog(studentSystem, response);
            }
        });
        return queryButton;
    }
}