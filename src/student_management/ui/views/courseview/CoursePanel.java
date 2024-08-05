package student_management.ui.views.courseview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.AbstractPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class CoursePanel extends AbstractPanelLayout {

    private CourseInputPanel inputPanel;
    private CourseButtonPanel buttonPanel;

    public CoursePanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
       super(studentClient, studentSystem, user);
    }

    @Override
    protected JPanel createInputPanel() {
       inputPanel = new CourseInputPanel();
       return inputPanel.getPanel();
    }

    @Override
    protected JPanel createButtonPanel() {
       buttonPanel = new CourseButtonPanel(studentClient, inputPanel, studentSystem, user);
       return buttonPanel.getPanel();
    }



}