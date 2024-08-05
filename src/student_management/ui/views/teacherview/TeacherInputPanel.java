package student_management.ui.views.teacherview;

import student_management.ui.layout.AbstractInputPanel;

import javax.swing.*;

import static student_management.ui.layout.UIComponentFactory.createComboBox;
import static student_management.ui.layout.UIComponentFactory.createTextField;

public class TeacherInputPanel extends AbstractInputPanel {
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField departmentIdField;

    @Override
    protected void initComponents() {
        teacherIdField = createTextField();
        teacherNameField = createTextField();
        teacherSubjectField = createTextField();
        ageField = createTextField();
        genderComboBox = createComboBox(new String[]{"男", "女"});
        departmentIdField = createTextField();

        addLabelAndField("教师ID:", teacherIdField, 0);
        addLabelAndField("教师姓名:", teacherNameField, 1);
        addLabelAndField("教授科目:", teacherSubjectField, 2);
        addLabelAndField("年龄:", ageField, 3);
        addLabelAndField("性别:", genderComboBox, 4);
        addLabelAndField("院系ID:", departmentIdField, 5);
    }

    public JTextField getTeacherIdField() {
        return teacherIdField;
    }

    public JTextField getTeacherNameField() {
        return teacherNameField;
    }

    public JTextField getTeacherSubjectField() {
        return teacherSubjectField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JTextField getDepartmentIdField() {
        return departmentIdField;
    }
}