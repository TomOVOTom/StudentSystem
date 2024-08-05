package student_management.ui.views.studentview;

import javax.swing.*;
import java.awt.*;

public class StudentInputPanel {
    private JPanel panel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentIdField;
    private JTextField departmentNameField;

    public StudentInputPanel() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        idField = createTextField();
        nameField = createTextField();
        ageField = createTextField();
        genderComboBox = createComboBox();
        classIdField = createTextField();
        classNameField = createTextField();
        departmentIdField = createTextField();
        departmentNameField = createTextField();

        addLabelAndField("学号:", idField, gbc, 0);
        addLabelAndField("姓名:", nameField, gbc, 1);
        addLabelAndField("年龄:", ageField, gbc, 2);
        addLabelAndField("性别:", genderComboBox, gbc, 3);
        addLabelAndField("班级ID:", classIdField, gbc, 4);
        addLabelAndField("班级名称:", classNameField, gbc, 5);
        addLabelAndField("院系ID:", departmentIdField, gbc, 6);
        addLabelAndField("院系名称:", departmentNameField, gbc, 7);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }

    private JComboBox<String> createComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"男", "女"});
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

    // Getter methods for all fields
    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getClassIdField() {
        return classIdField;
    }

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JTextField getClassNameField() {
        return classNameField;
    }

    public JTextField getDepartmentNameField() {
        return departmentNameField;
    }
}