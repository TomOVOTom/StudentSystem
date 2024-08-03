package student_management.ui.components.classcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Class;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;
    private User user;

    public AddClassButton(StudentClient studentClient, JTextField classIdField, JTextField classNameField, JTextField departmentField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton addClassButton = new JButton("添加班级");
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String department = departmentField.getText();
                Class newClass = new Class(classId, className, department);
                String response = studentClient.sendCommand("CLASS_ADD_CLASS", user, newClass);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addClassButton;
    }
}