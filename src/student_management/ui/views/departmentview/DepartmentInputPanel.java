package student_management.ui.views.departmentview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;

public class DepartmentInputPanel extends AbstractInputPanel {
    private JTextField departmentIdField;
    private JTextField departmentNameField;

    @Override
    protected void initComponents() {
        departmentIdField = UIComponentFactory.createTextField();
        departmentNameField = UIComponentFactory.createTextField();

        addLabelAndField("院系ID:", departmentIdField, 0);
        addLabelAndField("院系名称:", departmentNameField, 1);
    }

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }

    public JTextField getDepartmentNameField() {
        return departmentNameField;
    }
}