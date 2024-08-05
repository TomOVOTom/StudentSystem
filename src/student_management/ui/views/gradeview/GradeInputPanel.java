package student_management.ui.views.gradeview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;

public class GradeInputPanel extends AbstractInputPanel {
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;

    @Override
    protected void initComponents() {
        studentIdField = UIComponentFactory.createTextField();
        courseIdField = UIComponentFactory.createTextField();
        scoreField = UIComponentFactory.createTextField();

        addLabelAndField("学生ID:", studentIdField, 0);
        addLabelAndField("课程ID:", courseIdField, 1);
        addLabelAndField("成绩:", scoreField, 2);
    }

    public JTextField getStudentIdField() {
        return studentIdField;
    }

    public JTextField getCourseIdField() {
        return courseIdField;
    }

    public JTextField getScoreField() {
        return scoreField;
    }
}