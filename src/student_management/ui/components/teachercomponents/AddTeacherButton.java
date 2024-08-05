package student_management.ui.components.teachercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Teacher;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField subjectField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;

    public AddTeacherButton(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField subjectField, JTextField ageField, JComboBox<String> genderComboBox, JTextField departmentIdField, StudentSystem studentSystem) {
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
    JButton addTeacherButton = new JButton("添加老师");
    addTeacherButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                String subject = subjectField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderComboBox.getSelectedItem();
                String departmentId = departmentIdField.getText();
                Teacher teacher = new Teacher(id, name, subject, age, gender, departmentId);
                System.out.println("正在添加教师: " + teacher);
                String response = studentClient.sendCommand("TEACHER_ADD_TEACHER", teacher);
                System.out.println("服务器响应: " + response);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (Exception ex) {
                System.err.println("添加教师时出错: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(studentSystem, "添加教师失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    return addTeacherButton;
}
}