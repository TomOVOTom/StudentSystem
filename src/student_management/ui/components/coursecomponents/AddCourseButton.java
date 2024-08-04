package student_management.ui.components.coursecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseButton {
    private StudentClient studentClient;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JComboBox<String> gradingSystemComboBox;
    private StudentSystem studentSystem;

    private JTextField creditsField;
    private User user;

public AddCourseButton(StudentClient studentClient, JTextField courseIdField,
                       JTextField courseNameField, JTextField teacherField,
                       JTextField creditsField, JComboBox<String> gradingSystemComboBox,
                       StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.gradingSystemComboBox = gradingSystemComboBox;
        this.studentSystem = studentSystem;
        this.creditsField = creditsField;
        this.user = user;
    }

    public JButton createButton() {
        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.addActionListener(new ActionListener() {
           @Override
public void actionPerformed(ActionEvent e) {
    String courseId = courseIdField.getText();
    String courseName = courseNameField.getText();
    String teacherId = teacherField.getText();
    String gradingSystem = (String) gradingSystemComboBox.getSelectedItem();
    String credits = creditsField.getText();
    String response = studentClient.sendCommand("COURSE_ADD_COURSE", user, courseId, courseName, gradingSystem, teacherId, credits);
    JOptionPane.showMessageDialog(studentSystem, response);
    studentSystem.updateDisplay();
}
        });
        return addCourseButton;
    }
}