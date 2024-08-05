package student_management.ui.views.studentview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.studentcomponents.StudentButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class StudentButtonPanel {
    private JPanel panel;
    private StudentButtonHandler buttonHandler;

    public StudentButtonPanel(StudentClient studentClient, StudentInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, StudentInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new StudentButtonHandler(
                studentClient,
                inputPanel.getIdField(),
                inputPanel.getNameField(),
                inputPanel.getAgeField(),
                inputPanel.getGenderField(),
                inputPanel.getClassIdField(),
                inputPanel.getClassNameField(),
                inputPanel.getDepartmentIdField(),
                inputPanel.getDepartmentNameField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddStudentButton());
        panel.add(buttonHandler.createRemoveStudentButton());
        panel.add(buttonHandler.createUpdateStudentButton());
        panel.add(buttonHandler.createQueryStudentButton());
        panel.add(buttonHandler.createClearStudentFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}