package student_management.ui.button.student_class;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;
import student_management.util.LoggerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveStudentClassButton {
    private StudentClient studentClient;
    private JTextField classIdField;
    private StudentSystem studentSystem;

    public RemoveStudentClassButton(StudentClient studentClient, JTextField classIdField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton removeClassButton = new JButton("删除班级");
        removeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classId = classIdField.getText();
                String response = studentClient.sendCommand("CLASS_REMOVE_CLASS", classId);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
                LoggerUtil.log("删除班级: " + classId);
            }
        });
        return removeClassButton;
    }
}