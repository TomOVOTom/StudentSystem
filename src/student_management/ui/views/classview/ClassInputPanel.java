package student_management.ui.views.classview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;

public class ClassInputPanel extends AbstractInputPanel {
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;

    @Override
    protected void initComponents() {
        classIdField = UIComponentFactory.createTextField();
        classNameField = UIComponentFactory.createTextField();
        departmentField = UIComponentFactory.createTextField();

        addLabelAndField("班级ID:", classIdField, 0);
        addLabelAndField("班级名称:", classNameField, 1);
        addLabelAndField("所属院系:", departmentField, 2);
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