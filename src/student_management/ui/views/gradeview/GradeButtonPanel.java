package student_management.ui.views.gradeview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.gradecomponents.GradeButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class GradeButtonPanel {
    private JPanel panel;
    private GradeButtonHandler buttonHandler;

    public GradeButtonPanel(StudentClient studentClient, GradeInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, GradeInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new GradeButtonHandler(
                studentClient,
                inputPanel.getStudentIdField(),
                inputPanel.getCourseIdField(),
                inputPanel.getScoreField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddGradeButton());
        panel.add(buttonHandler.createRemoveGradeButton());
        panel.add(buttonHandler.createUpdateGradeButton());
        panel.add(buttonHandler.createQueryGradeButton());
        panel.add(buttonHandler.createClearGradeFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}