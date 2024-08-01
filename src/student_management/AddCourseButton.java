package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public AddCourseButton(StudentClient studentClient, JTextField idField, JTextField courseField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                int grade = Integer.parseInt(gradeField.getText());
                String response = studentClient.sendCommand("ADD_COURSE", id, course, grade);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return addCourseButton;
    }
}