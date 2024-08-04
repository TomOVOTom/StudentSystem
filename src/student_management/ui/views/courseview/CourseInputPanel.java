package student_management.ui.views.courseview;

import javax.swing.*;
import java.awt.*;

public class CourseInputPanel {
    private JPanel panel;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherIdField;
    private JComboBox<String> gradingSystemComboBox;

    private JTextField creditsField;


    public CourseInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        courseIdField = createTextField();
        courseNameField = createTextField();
        teacherIdField = createTextField();
        gradingSystemComboBox = createComboBox();
        creditsField = createTextField();

        addLabelAndField("课程编号:", courseIdField, gbc, 0);
        addLabelAndField("课程名称:", courseNameField, gbc, 1);
        addLabelAndField("教师ID:", teacherIdField, gbc, 2);
        addLabelAndField("评分方式:", gradingSystemComboBox, gbc, 3);
        addLabelAndField("学分:", creditsField, gbc, 4);

    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }

    private JComboBox<String> createComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"百分制", "等级制(A,B,C,D,F)"});
        comboBox.setPreferredSize(new Dimension(comboBox.getPreferredSize().width, 40));
        return comboBox;
    }

    private void addLabelAndField(String labelText, JComponent field, GridBagConstraints gbc, int gridy) {
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

    public JTextField getCourseIdField() {
        return courseIdField;
    }

    public JTextField getCourseNameField() {
        return courseNameField;
    }

    public JTextField getTeacherIdField() {
        return teacherIdField;
    }

    public JTextField getCreditsField() {
        return creditsField;
    }

    public JComboBox<String> getGradingSystemComboBox() {
        return gradingSystemComboBox;
    }
}