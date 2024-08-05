package student_management.ui.components.studentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

    public AddStudentButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField genderField, JTextField classIdField, JTextField classNameField, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.genderField = genderField;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
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
                    String gender = genderField.getText();
                    String classId = classIdField.getText();
                    String className = classNameField.getText();
                    String departmentId = departmentIdField.getText();
                    String departmentName = departmentNameField.getText();
                    String response = studentClient.sendCommand("STUDENT_ADD_STUDENT", user, id, name, age, gender, classId, className, departmentId, departmentName);
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