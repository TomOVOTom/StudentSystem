package student_management.ui.views.classview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.classcomponents.ClassButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class ClassButtonPanel {
    private JPanel panel;
    private ClassButtonHandler buttonHandler;

    public ClassButtonPanel(StudentClient studentClient, ClassInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, ClassInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new ClassButtonHandler(
                studentClient,
                inputPanel.getClassIdField(),
                inputPanel.getClassNameField(),
                inputPanel.getDepartmentField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddStudentClassButton());
        panel.add(buttonHandler.createRemoveStudentClassButton());
        panel.add(buttonHandler.createUpdateStudentClassButton());
        panel.add(buttonHandler.createQueryStudentClassButton());
        panel.add(buttonHandler.createClearStudentClassFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}