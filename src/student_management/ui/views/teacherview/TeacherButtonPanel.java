package student_management.ui.views.teacherview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.teachercomponents.TeacherButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class TeacherButtonPanel {
    private JPanel panel;
    private TeacherButtonHandler buttonHandler;

    public TeacherButtonPanel(StudentClient studentClient, TeacherInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, TeacherInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new TeacherButtonHandler(
            studentClient,
            inputPanel.getTeacherIdField(),
            inputPanel.getTeacherNameField(),
            inputPanel.getTeacherSubjectField(),
            inputPanel.getAgeField(),
            inputPanel.getGenderComboBox(),
            inputPanel.getDepartmentIdField(),
            studentSystem,
                user
        );

        panel.add(buttonHandler.createAddTeacherButton());
        panel.add(buttonHandler.createRemoveTeacherButton());
        panel.add(buttonHandler.createUpdateTeacherButton());
        panel.add(buttonHandler.createQueryTeacherButton());
        panel.add(buttonHandler.createClearTeacherFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}