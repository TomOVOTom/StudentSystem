package student_management.ui.button.student;

import student_management.client.StudentClient;
import student_management.model.Student;
import student_management.ui.StudentSystem;
import student_management.util.LoggerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private StudentSystem studentSystem;

    public AddStudentButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Student student = new Student(id, name, age);
                String response = studentClient.sendCommand("STUDENT_ADD_STUDENT", student);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
                LoggerUtil.log("添加学生: " + student.toString());
            }
        });
        return addButton;
    }
}