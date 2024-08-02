package student_management.ui.button.student;

import student_management.client.StudentClient;
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
        JButton addStudentButton = new JButton("添加学生");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String classId = classIdField.getText();
                    String departmentId = departmentIdField.getText();
                    String response = studentClient.sendCommand("STUDENT_ADD_STUDENT", user, id, name, age, classId, departmentId);
                    JOptionPane.showMessageDialog(studentSystem, response);
                    studentSystem.updateDisplay();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(studentSystem, "年龄必须是数字", "输入错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return addStudentButton;
    }
}