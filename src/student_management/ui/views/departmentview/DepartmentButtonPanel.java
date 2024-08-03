package student_management.ui.views.departmentview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.departmentcomponents.DepartmentButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class DepartmentButtonPanel {
    private JPanel panel;
    private DepartmentButtonHandler buttonHandler;

    public DepartmentButtonPanel(StudentClient studentClient, DepartmentInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, DepartmentInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new DepartmentButtonHandler(
                studentClient,
                inputPanel.getDepartmentIdField(),
                inputPanel.getDepartmentNameField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddDepartmentButton());
        panel.add(buttonHandler.createRemoveDepartmentButton());
        panel.add(buttonHandler.createUpdateDepartmentButton());
        panel.add(buttonHandler.createQueryDepartmentButton());
        panel.add(buttonHandler.createClearDepartmentFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}