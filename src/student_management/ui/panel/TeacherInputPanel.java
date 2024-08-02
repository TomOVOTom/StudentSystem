package student_management.ui.panel;

import javax.swing.*;
import java.awt.*;

public class TeacherInputPanel {
    private JPanel panel;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;

    public TeacherInputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        teacherIdField = new JTextField(15);
        teacherNameField = new JTextField(15);
        teacherSubjectField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("老师编号:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(teacherIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("老师姓名:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(teacherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("老师科目:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(teacherSubjectField, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTeacherIdField() {
        return teacherIdField;
    }

    public JTextField getTeacherNameField() {
        return teacherNameField;
    }

    public JTextField getTeacherSubjectField() {
        return teacherSubjectField;
    }
}