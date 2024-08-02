package student_management.ui.button.teacher;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField subjectField;
    private StudentSystem studentSystem;

    public UpdateTeacherButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField subjectField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.subjectField = subjectField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton updateTeacherButton = new JButton("更新老师");
        updateTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String subject = subjectField.getText();
                String response = studentClient.sendCommand("TEACHER_UPDATE_TEACHER", id, name, subject);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateTeacherButton;
    }
}