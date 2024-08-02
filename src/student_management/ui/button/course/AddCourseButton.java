package student_management.ui.button.course;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public AddCourseButton(StudentClient studentClient, JTextField idField, JTextField courseIdField, JTextField courseNameField, JTextField teacherField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    String courseId = courseIdField.getText();
                    String courseName = courseNameField.getText();
                    String teacher = teacherField.getText();
                    int grade = Integer.parseInt(gradeField.getText());
                    String response = studentClient.sendCommand("STUDENT_ADD_COURSE", studentSystem.getUser(), id, courseId, courseName, teacher, grade);
                    JOptionPane.showMessageDialog(studentSystem, response);
                    studentSystem.updateDisplay();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(studentSystem, "成绩必须是数字", "输入错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return addCourseButton;
    }
}