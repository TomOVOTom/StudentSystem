package student_management.ui.views.teacherview;

import javax.swing.*;
import java.awt.*;

public class TeacherInputPanel {
    private JPanel panel;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField departmentIdField;

    public TeacherInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        teacherIdField = createTextField();
        teacherNameField = createTextField();
        teacherSubjectField = createTextField();
        ageField = createTextField();
        genderComboBox = new JComboBox<>(new String[]{"男", "女"});
        departmentIdField = createTextField();

        addLabelAndField("教师ID:", teacherIdField, gbc, 0);
        addLabelAndField("教师姓名:", teacherNameField, gbc, 1);
        addLabelAndField("教授科目:", teacherSubjectField, gbc, 2);
        addLabelAndField("年龄:", ageField, gbc, 3);
        addLabelAndField("性别:", genderComboBox, gbc, 4);
        addLabelAndField("院系ID:", departmentIdField, gbc, 5);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
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

    public JTextField getTeacherIdField() {
        return teacherIdField;
    }

    public JTextField getTeacherNameField() {
        return teacherNameField;
    }

    public JTextField getTeacherSubjectField() {
        return teacherSubjectField;
    }
    public JTextField getAgeField() {
        return ageField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }
}