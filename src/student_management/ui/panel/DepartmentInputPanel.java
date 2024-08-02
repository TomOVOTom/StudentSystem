package student_management.ui.panel;

import javax.swing.*;
import java.awt.*;

public class DepartmentInputPanel {
    private JPanel panel;
    private JTextField departmentIdField;
    private JTextField departmentNameField;

    public DepartmentInputPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        departmentIdField = new JTextField(15);
        departmentNameField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("院系编号:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(departmentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("院系名称:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(departmentNameField, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }

    public JTextField getDepartmentNameField() {
        return departmentNameField;
    }
}