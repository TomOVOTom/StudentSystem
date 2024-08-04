package student_management.ui.views.courseview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.coursecomponents.CourseButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class CourseButtonPanel {
    private JPanel panel;
    private CourseButtonHandler buttonHandler;

    public CourseButtonPanel(StudentClient studentClient, CourseInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, CourseInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new CourseButtonHandler(
                studentClient,
                inputPanel.getCourseIdField(),
                inputPanel.getCourseNameField(),
                inputPanel.getTeacherIdField(),
                inputPanel.getCreditsField(),
                inputPanel.getGradingSystemComboBox(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddCourseButton());
        panel.add(buttonHandler.createRemoveCourseButton());
        panel.add(buttonHandler.createUpdateCourseButton());
        panel.add(buttonHandler.createQueryCourseButton());
        panel.add(buttonHandler.createClearCourseFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}