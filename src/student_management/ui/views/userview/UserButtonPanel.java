package student_management.ui.views.userview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.components.usercomponents.UserButtonHandler;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class UserButtonPanel {
    private JPanel panel;
    private UserButtonHandler buttonHandler;

    public UserButtonPanel(StudentClient studentClient, UserInputPanel inputPanel, StudentSystem studentSystem, User user) {
        initComponents(studentClient, inputPanel, studentSystem, user);
    }

    private void initComponents(StudentClient studentClient, UserInputPanel inputPanel, StudentSystem studentSystem, User user) {
        panel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonHandler = new UserButtonHandler(
                studentClient,
                inputPanel.getUsernameField(),
                inputPanel.getPasswordField(),
                inputPanel.getRoleField(),
                studentSystem,
                user
        );

        panel.add(buttonHandler.createAddUserButton());
        panel.add(buttonHandler.createRemoveUserButton());
        panel.add(buttonHandler.createUpdateUserButton());
        panel.add(buttonHandler.createQueryUserButton());
        panel.add(buttonHandler.createClearUserFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}