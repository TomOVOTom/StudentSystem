package student_management.ui.views.userview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.CommonPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class UserPanel extends CommonPanelLayout {
    private UserInputPanel inputPanel;
    private UserButtonPanel buttonPanel;

    public UserPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        super(studentClient, studentSystem, user);
    }

    @Override
    protected JPanel createInputPanel() {
        inputPanel = new UserInputPanel();
        return inputPanel.getPanel();
    }

    @Override
    protected JPanel createButtonPanel() {
        buttonPanel = new UserButtonPanel(studentClient, inputPanel, studentSystem, user);
        return buttonPanel.getPanel();
    }
}