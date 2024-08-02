package student_management.ui.panel;

import javax.swing.*;
import java.awt.*;

public class CourseInputPanel {
    private JPanel panel;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JTextField gradeField;

    public CourseInputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        courseIdField = new JTextField(15);
        courseNameField = new JTextField(15);
        teacherField = new JTextField(15);
        gradeField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("课程编号:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(courseIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("课程名称:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("教师:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(teacherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panel.add(new JLabel("成绩:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(gradeField, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getCourseIdField() {
        return courseIdField;
    }

    public JTextField getCourseNameField() {
        return courseNameField;
    }

    public JTextField getTeacherField() {
        return teacherField;
    }

    public JTextField getGradeField() {
        return gradeField;
    }
}