package student_management.ui.components.teachercomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Teacher;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class TeacherButtonHandler {
    private StudentClient studentClient;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField departmentIdField;
    private StudentSystem studentSystem;
    private User user;

    public TeacherButtonHandler(StudentClient studentClient, JTextField teacherIdField, JTextField teacherNameField, JTextField teacherSubjectField, JTextField ageField, JComboBox<String> genderComboBox, JTextField departmentIdField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.teacherIdField = teacherIdField;
        this.teacherNameField = teacherNameField;
        this.teacherSubjectField = teacherSubjectField;
        this.ageField = ageField;
        this.genderComboBox = genderComboBox;
        this.departmentIdField = departmentIdField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddTeacherButton() {
        JButton addButton = new JButton("添加老师");
        addButton.addActionListener(e -> {
            try {
                String id = teacherIdField.getText();
                String name = teacherNameField.getText();
                String subject = teacherSubjectField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderComboBox.getSelectedItem();
                String departmentId = departmentIdField.getText();
                Teacher teacher = new Teacher(id, name, subject, age, gender, departmentId);
                String response = studentClient.sendCommand("TEACHER_ADD_TEACHER", user, teacher);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(studentSystem, "添加老师失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return addButton;
    }

    public JButton createRemoveTeacherButton() {
        JButton removeButton = new JButton("删除老师");
        removeButton.addActionListener(e -> {
            String id = teacherIdField.getText();
            String response = studentClient.sendCommand("TEACHER_REMOVE_TEACHER", user, id);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateTeacherButton() {
        JButton updateButton = new JButton("更新老师");
        updateButton.addActionListener(e -> {
            try {
                String id = teacherIdField.getText();
                String name = teacherNameField.getText();
                String subject = teacherSubjectField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderComboBox.getSelectedItem();
                String departmentId = departmentIdField.getText();
                Teacher teacher = new Teacher(id, name, subject, age, gender, departmentId);
                String response = studentClient.sendCommand("TEACHER_UPDATE_TEACHER", user, teacher);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(studentSystem, "更新老师失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return updateButton;
    }

    public JButton createQueryTeacherButton() {
        JButton queryButton = new JButton("查询老师");
        queryButton.addActionListener(e -> {
            String id = teacherIdField.getText();
            String response = studentClient.sendCommand("TEACHER_QUERY_TEACHER", user, id);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }

    public JButton createClearTeacherFieldsButton() {
        JButton clearButton = new JButton("清空老师输入框");
        clearButton.addActionListener(e -> {
            teacherIdField.setText("");
            teacherNameField.setText("");
            teacherSubjectField.setText("");
            ageField.setText("");
            genderComboBox.setSelectedIndex(0);
            departmentIdField.setText("");
        });
        return clearButton;
    }
}