package student_management.ui.views.studentcourseview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;

public class StudentCourseInputPanel extends AbstractInputPanel {
    private JTextField studentIdField;
    private JTextField courseIdField;

    @Override
    protected void initComponents() {
        studentIdField = UIComponentFactory.createTextField();
        courseIdField = UIComponentFactory.createTextField();

        addLabelAndField("学生学号:", studentIdField, 0);
        addLabelAndField("课程编号:", courseIdField, 1);
    }

    public JTextField getStudentIdField() {
        return studentIdField;
    }

    public JTextField getCourseIdField() {
        return courseIdField;
    }
}