package student_management.ui.panel;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;
import student_management.ui.button.ButtonHandler;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel {
    private JPanel panel;

    public ButtonPanel(StudentClient studentClient, InputPanel inputPanel, StudentSystem studentSystem) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        ButtonHandler buttonHandler = new ButtonHandler(
            studentClient,
            inputPanel.getIdField(),
            inputPanel.getNameField(),
            inputPanel.getAgeField(),
            inputPanel.getCourseIdField(),
            inputPanel.getCourseNameField(),
            inputPanel.getTeacherField(),
            inputPanel.getGradeField(),
            inputPanel.getTeacherIdField(),  // 修改这里
            inputPanel.getTeacherNameField(),
            inputPanel.getTeacherSubjectField(),
            studentSystem
        );

        panel.add(buttonHandler.createAddStudentButton());
        panel.add(buttonHandler.createRemoveStudentButton());
        panel.add(buttonHandler.createUpdateStudentButton());
        panel.add(buttonHandler.createAddCourseButton());
        panel.add(buttonHandler.createRemoveCourseButton());
        panel.add(buttonHandler.createUpdateCourseButton());
        panel.add(buttonHandler.createQueryStudentButton());
        panel.add(buttonHandler.createQueryCourseButton());
        panel.add(buttonHandler.createAddTeacherButton());
        panel.add(buttonHandler.createRemoveTeacherButton());
        panel.add(buttonHandler.createUpdateTeacherButton());
        panel.add(buttonHandler.createQueryTeacherButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}