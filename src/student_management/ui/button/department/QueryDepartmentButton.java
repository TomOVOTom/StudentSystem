package student_management.ui.button.department;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;

    public QueryDepartmentButton(StudentClient studentClient, JTextField departmentIdField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton queryDepartmentButton = new JButton("查询院系");
        queryDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String result = studentClient.sendCommand("DEPARTMENT_QUERY_DEPARTMENT", departmentId);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryDepartmentButton;
    }
}