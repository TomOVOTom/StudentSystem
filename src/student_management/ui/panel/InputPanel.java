package student_management.ui.panel;

import javax.swing.*;
import java.awt.*;

public class InputPanel {
    private JPanel panel;
    private StudentInputPanel studentInputPanel;
    private CourseInputPanel courseInputPanel;
    private TeacherInputPanel teacherInputPanel;

    public InputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        studentInputPanel = new StudentInputPanel();
        courseInputPanel = new CourseInputPanel();
        teacherInputPanel = new TeacherInputPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(studentInputPanel.getPanel(), gbc);

        gbc.gridy = 1;
        panel.add(courseInputPanel.getPanel(), gbc);

        gbc.gridy = 2;
        panel.add(teacherInputPanel.getPanel(), gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getIdField() {
        return studentInputPanel.getIdField();
    }

    public JTextField getNameField() {
        return studentInputPanel.getNameField();
    }

    public JTextField getAgeField() {
        return studentInputPanel.getAgeField();
    }

    public JTextField getCourseIdField() {
        return courseInputPanel.getCourseIdField();
    }

    public JTextField getCourseNameField() {
        return courseInputPanel.getCourseNameField();
    }

    public JTextField getTeacherField() {
        return courseInputPanel.getTeacherField();
    }

    public JTextField getGradeField() {
        return courseInputPanel.getGradeField();
    }

    public JTextField getTeacherIdField() {
        return teacherInputPanel.getTeacherIdField();
    }

    public JTextField getTeacherNameField() {
        return teacherInputPanel.getTeacherNameField();
    }

    public JTextField getTeacherSubjectField() {
        return teacherInputPanel.getTeacherSubjectField();
    }
}