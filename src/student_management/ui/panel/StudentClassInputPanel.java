package student_management.ui.panel;

import javax.swing.*;
import java.awt.*;

public class StudentClassInputPanel {
    private JPanel panel;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;

    public StudentClassInputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        classIdField = new JTextField(15);
        classNameField = new JTextField(15);
        departmentField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("班级编号:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(classIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("班级名称:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(classNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("院系:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(departmentField, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getClassIdField() {
        return classIdField;
    }

    public JTextField getClassNameField() {
        return classNameField;
    }

    public JTextField getDepartmentField() {
        return departmentField;
    }
}