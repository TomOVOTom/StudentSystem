package student_management.ui.views.userview;

import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class UserInputPanel {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public UserInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        usernameField = UIComponentFactory.createTextField();
        passwordField = UIComponentFactory.createPasswordField();
        roleComboBox = UIComponentFactory.createComboBox(new String[]{"admin", "teacher", "student"});

        addLabelAndField("用户名:", usernameField, gbc, 0);
        addLabelAndField("密码:", passwordField, gbc, 1);
        addLabelAndField("角色:", roleComboBox, gbc, 2);
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

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }
}