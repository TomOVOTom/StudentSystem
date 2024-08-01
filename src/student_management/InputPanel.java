package student_management;

import javax.swing.*;
import java.awt.*;

public class InputPanel {
    private JPanel panel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField gradeField;

    public InputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(15);
        nameField = new JTextField(15);
        ageField = new JTextField(15);
        courseField = new JTextField(15);
        gradeField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("学号:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("姓名:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("年龄:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panel.add(new JLabel("课程:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(courseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panel.add(new JLabel("成绩:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(gradeField, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getCourseField() {
        return courseField;
    }

    public JTextField getGradeField() {
        return gradeField;
    }
}