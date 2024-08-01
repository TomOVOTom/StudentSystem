package student_management.ui.button.course;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCourseButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseField;
    private StudentSystem studentSystem;

    public RemoveCourseButton(StudentClient studentClient, JTextField idField, JTextField courseField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseField = courseField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton removeCourseButton = new JButton("删除课程");
        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                String response = studentClient.sendCommand("REMOVE_COURSE", id, course);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeCourseButton;
    }
}