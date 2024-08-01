package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public UpdateCourseButton(StudentClient studentClient, JTextField idField, JTextField courseField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton updateCourseButton = new JButton("修改课程");
        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                int grade = Integer.parseInt(gradeField.getText());
                String response = studentClient.sendCommand("UPDATE_COURSE", id, course, grade);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            }
        });
        return updateCourseButton;
    }
}