package student_management.ui.components.departmentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;
    private User user;

    public QueryDepartmentButton(StudentClient studentClient, JTextField departmentIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton queryDepartmentButton = new JButton("查询院系");
        queryDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String result = studentClient.sendCommand("DEPARTMENT_QUERY_DEPARTMENT", user, departmentId);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryDepartmentButton;
    }
}