package student_management.ui.button.student_class;

import student_management.client.StudentClient;
import student_management.model.StudentClass;
import student_management.ui.StudentSystem;
import student_management.util.LoggerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;

    public UpdateStudentClassButton(StudentClient studentClient, JTextField classIdField, JTextField classNameField, JTextField departmentField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton updateClassButton = new JButton("更新班级");
        updateClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String className = classNameField.getText();
                String department = departmentField.getText();
                StudentClass updatedClass = new StudentClass(classId, className, department);
                String response = studentClient.sendCommand("CLASS_UPDATE_CLASS", updatedClass);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
                LoggerUtil.log("更新班级: " + updatedClass.toString());
            }
        });
        return updateClassButton;
    }
}