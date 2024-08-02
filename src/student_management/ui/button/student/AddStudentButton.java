package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField classIdField;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;
    private User user;

    public AddStudentButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField classIdField, JTextField departmentIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.classIdField = classIdField;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createButton() {
        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String classId = classIdField.getText();
                String departmentId = departmentIdField.getText();
                Student student = new Student(id, name, age, classId, departmentId);
                String response = studentClient.sendCommand("STUDENT_ADD_STUDENT", user, student);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addButton;
    }
}