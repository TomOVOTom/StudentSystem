package student_management.ui.views.gradeview;

import javax.swing.*;
import java.awt.*;

public class GradeInputPanel {
    private JPanel panel;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;

    public GradeInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        studentIdField = createTextField();
        courseIdField = createTextField();
        scoreField = createTextField();

        addLabelAndField("学生ID:", studentIdField, gbc, 0);
        addLabelAndField("课程ID:", courseIdField, gbc, 1);
        addLabelAndField("成绩:", scoreField, gbc, 2);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }

    private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int gridy) {
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getStudentIdField() {
        return studentIdField;
    }

    public JTextField getCourseIdField() {
        return courseIdField;
    }

    public JTextField getScoreField() {
        return scoreField;
    }
}