package student_management.ui.views.classview;

import javax.swing.*;
import java.awt.*;

public class ClassInputPanel {
    private JPanel panel;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;

    public ClassInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        classIdField = createTextField();
        classNameField = createTextField();
        departmentField = createTextField();

        addLabelAndField("班级ID:", classIdField, gbc, 0);
        addLabelAndField("班级名称:", classNameField, gbc, 1);
        addLabelAndField("所属院系:", departmentField, gbc, 2);
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