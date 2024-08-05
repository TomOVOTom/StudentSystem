package student_management.ui.views.studentview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class StudentInputPanel extends AbstractInputPanel {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentIdField;
    private JTextField departmentNameField;

    @Override
    protected void initComponents() {
        idField = UIComponentFactory.createTextField();
        nameField = UIComponentFactory.createTextField();
        ageField = UIComponentFactory.createTextField();
        genderComboBox = UIComponentFactory.createComboBox(new String[]{"男", "女"});
        classIdField = UIComponentFactory.createTextField();
        classNameField = UIComponentFactory.createTextField();
        departmentIdField = UIComponentFactory.createTextField();
        departmentNameField = UIComponentFactory.createTextField();

        addLabelAndField("学号:", idField, 0);
        addLabelAndField("姓名:", nameField, 1);
        addLabelAndField("年龄:", ageField, 2);
        addLabelAndField("性别:", genderComboBox, 3);
        addLabelAndField("班级ID:", classIdField, 4);
        addLabelAndField("班级名称:", classNameField, 5);
        addLabelAndField("院系ID:", departmentIdField, 6);
        addLabelAndField("院系名称:", departmentNameField, 7);
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