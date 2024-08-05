package student_management.ui.components.teachercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Teacher;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField subjectField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;

    public UpdateTeacherButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField subjectField, JTextField ageField, JComboBox<String> genderComboBox, JTextField departmentIdField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.subjectField = subjectField;
        this.ageField = ageField;
        this.genderComboBox = genderComboBox;
        this.departmentIdField = departmentIdField;
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
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderComboBox.getSelectedItem();
                String departmentId = departmentIdField.getText();
                Teacher teacher = new Teacher(id, name, subject, age, gender, departmentId);
                String response = studentClient.sendCommand("TEACHER_UPDATE_TEACHER", teacher);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateTeacherButton;
    }
}