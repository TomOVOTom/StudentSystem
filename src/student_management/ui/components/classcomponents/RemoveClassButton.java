package student_management.ui.components.classcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private StudentSystem studentSystem;
    private User user;

    public RemoveClassButton(StudentClient studentClient, JTextField classIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton removeClassButton = new JButton("删除班级");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String response = studentClient.sendCommand("CLASS_REMOVE_CLASS", user, classId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return removeClassButton;
    }
}