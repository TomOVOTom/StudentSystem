package student_management.ui.button.teacher;

import student_management.client.StudentClient;
import student_management.model.Teacher;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField subjectField;
    private StudentSystem studentSystem;

    public AddTeacherButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField subjectField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.subjectField = subjectField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addTeacherButton = new JButton("添加老师");
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String subject = subjectField.getText();
                Teacher teacher = new Teacher(id, name, subject);
                String response = studentClient.sendCommand("TEACHER_ADD_TEACHER", teacher);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addTeacherButton;
    }
}