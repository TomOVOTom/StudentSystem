package student_management.ui.views.studentcourseview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.studentcoursecomponents.StudentCourseButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class StudentCourseButtonPanel {
    private JPanel panel;
    private StudentCourseButtonHandler buttonHandler;

    public StudentCourseButtonPanel(StudentClient studentClient, StudentCourseInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, StudentCourseInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 2, 5, 5));

        buttonHandler = new StudentCourseButtonHandler(
                studentClient,
                inputPanel.getStudentIdField(),
                inputPanel.getCourseIdField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createSelectCourseButton());
        panel.add(buttonHandler.createDropCourseButton());
        panel.add(buttonHandler.createQueryStudentCoursesButton());
        panel.add(buttonHandler.createClearFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}