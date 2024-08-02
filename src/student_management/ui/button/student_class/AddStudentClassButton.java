package student_management.ui.button.student_class;

import student_management.client.StudentClient;
import student_management.model.StudentClass;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;

    public AddStudentClassButton(StudentClient studentClient, JTextField classIdField, JTextField classNameField, JTextField departmentField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addClassButton = new JButton("添加班级");
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String department = departmentField.getText();
                StudentClass newClass = new StudentClass(classId, className, department);
                String response = studentClient.sendCommand("CLASS_ADD_CLASS", newClass);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addClassButton;
    }
}