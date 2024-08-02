package student_management.ui.button.teacher;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private StudentSystem studentSystem;

    public QueryTeacherButton(StudentClient studentClient, JTextField idField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton button = new JButton("查询老师");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    String response = studentClient.sendCommand("TEACHER_QUERY_TEACHER", id);
                    JOptionPane.showMessageDialog(studentSystem, response);
                    studentSystem.updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(studentSystem, "请输入老师编号");
                }
            }
        });
        return button;
    }
}