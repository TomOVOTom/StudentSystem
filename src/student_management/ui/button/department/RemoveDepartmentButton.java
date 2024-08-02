package student_management.ui.button.department;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveDepartmentButton {
    private StudentClient studentClient;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;

    public RemoveDepartmentButton(StudentClient studentClient, JTextField departmentIdField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton removeDepartmentButton = new JButton("删除院系");
        removeDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentId = departmentIdField.getText();
                String response = studentClient.sendCommand("DEPARTMENT_REMOVE_DEPARTMENT", departmentId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeDepartmentButton;
    }
}