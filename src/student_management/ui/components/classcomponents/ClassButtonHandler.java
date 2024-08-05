package student_management.ui.components.classcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;
import student_management.model.entity.Class;

import javax.swing.*;

public class ClassButtonHandler {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;
    private User user;

    public ClassButtonHandler(StudentClient studentClient, JTextField classIdField,
                              JTextField classNameField, JTextField departmentField,
                              StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

   public JButton createAddStudentClassButton() {
    JButton addButton = new JButton("添加班级");
    addButton.addActionListener(e -> {
        String classId = classIdField.getText();
        String className = classNameField.getText();
        String department = departmentField.getText();
        Class newClass = new Class(classId, className, department);
        String response = studentClient.sendCommand("CLASS_ADD", user, newClass);
        JOptionPane.showMessageDialog(studentSystem, response);
        studentSystem.updateDisplay();
    });
    return addButton;
}

    public JButton createRemoveStudentClassButton() {
        JButton removeButton = new JButton("删除班级");
        removeButton.addActionListener(e -> {
            String classId = classIdField.getText();
            String response = studentClient.sendCommand("CLASS_REMOVE", user, classId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateStudentClassButton() {
    JButton updateButton = new JButton("更新班级");
    updateButton.addActionListener(e -> {
        String classId = classIdField.getText();
        String className = classNameField.getText();
        String department = departmentField.getText();
        Class updatedClass = new Class(classId, className, department);
        String response = studentClient.sendCommand("CLASS_UPDATE", user, updatedClass);
        JOptionPane.showMessageDialog(studentSystem, response);
        studentSystem.updateDisplay();
    });
    return updateButton;

    }

    public JButton createQueryStudentClassButton() {
        JButton queryButton = new JButton("查询班级");
        queryButton.addActionListener(e -> {
            String classId = classIdField.getText();
            String response = studentClient.sendCommand("CLASS_QUERY", user, classId);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }

    public JButton createClearStudentClassFieldsButton() {
        JButton clearButton = new JButton("清空班级输入框");
        clearButton.addActionListener(e -> {
            classIdField.setText("");
            classNameField.setText("");
            departmentField.setText("");
        });
        return clearButton;
    }
}