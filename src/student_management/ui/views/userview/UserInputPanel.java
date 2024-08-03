package student_management.ui.views.userview;

import javax.swing.*;
import java.awt.*;

public class UserInputPanel {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;

    public UserInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        usernameField = createTextField();
        passwordField = createPasswordField();
        roleField = createTextField();

        addLabelAndField("用户名:", usernameField, gbc, 0);
        addLabelAndField("密码:", passwordField, gbc, 1);
        addLabelAndField("角色:", roleField, gbc, 2);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField(20);
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

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getRoleField() {
        return roleField;
    }
}