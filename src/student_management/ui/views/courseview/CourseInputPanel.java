package student_management.ui.views.courseview;

import student_management.ui.layout.AbstractInputPanel;
import student_management.ui.layout.UIComponentFactory;

import javax.swing.*;

public class CourseInputPanel extends AbstractInputPanel {
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherIdField;
    private JComboBox<String> gradingSystemComboBox;
    private JTextField creditsField;

    @Override
    protected void initComponents() {
        courseIdField = UIComponentFactory.createTextField();
        courseNameField = UIComponentFactory.createTextField();
        teacherIdField = UIComponentFactory.createTextField();
        gradingSystemComboBox = UIComponentFactory.createComboBox(new String[]{"百分制", "等级制(A,B,C,D,F)"});
        creditsField = UIComponentFactory.createTextField();

        addLabelAndField("课程编号:", courseIdField, 0);
        addLabelAndField("课程名称:", courseNameField, 1);
        addLabelAndField("教师ID:", teacherIdField, 2);
        addLabelAndField("评分方式:", gradingSystemComboBox, 3);
        addLabelAndField("学分:", creditsField, 4);
    }

    public JTextField getCourseIdField() {
        return courseIdField;
    }

    public JTextField getCourseNameField() {
        return courseNameField;
    }

    public JTextField getTeacherIdField() {
        return teacherIdField;
    }

    public JTextField getCreditsField() {
        return creditsField;
    }

    public JComboBox<String> getGradingSystemComboBox() {
        return gradingSystemComboBox;
    }
}