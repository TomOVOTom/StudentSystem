package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private StudentSystem studentSystem;
    private User user;

    public UpdateStudentButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton updateButton = new JButton("更新学生");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String response = studentClient.sendCommand("STUDENT_UPDATE_STUDENT", user, id, name, age);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateButton;
    }
}