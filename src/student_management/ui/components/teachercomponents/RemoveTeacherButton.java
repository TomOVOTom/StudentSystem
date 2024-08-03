package student_management.ui.components.teachercomponents;

import student_management.client.StudentClient;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private StudentSystem studentSystem;

    public RemoveTeacherButton(StudentClient studentClient, JTextField idField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton removeTeacherButton = new JButton("删除老师");
        removeTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String response = studentClient.sendCommand("TEACHER_REMOVE_TEACHER", id);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeTeacherButton;
    }
}