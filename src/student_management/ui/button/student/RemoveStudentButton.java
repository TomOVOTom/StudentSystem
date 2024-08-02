package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private StudentSystem studentSystem;
    private User user;

    public RemoveStudentButton(StudentClient studentClient, JTextField idField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String response = studentClient.sendCommand("STUDENT_REMOVE_STUDENT", user, id);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeButton;
    }
}