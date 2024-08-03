package student_management.ui.views.departmentview;

import javax.swing.*;
import java.awt.*;

public class DepartmentInputPanel {
    private JPanel panel;
    private JTextField departmentIdField;
    private JTextField departmentNameField;

    public DepartmentInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        departmentIdField = createTextField();
        departmentNameField = createTextField();

        addLabelAndField("院系ID:", departmentIdField, gbc, 0);
        addLabelAndField("院系名称:", departmentNameField, gbc, 1);
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

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }

    public JTextField getDepartmentNameField() {
        return departmentNameField;
    }
}