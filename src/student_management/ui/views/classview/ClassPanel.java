package student_management.ui.views.classview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.AbstractPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class ClassPanel extends AbstractPanelLayout {
    private ClassInputPanel inputPanel;
    private ClassButtonPanel buttonPanel;

    public ClassPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        super(studentClient, studentSystem, user);
    }

    @Override
    protected JPanel createInputPanel() {
        inputPanel = new ClassInputPanel();
        return inputPanel.getPanel();
    }

    @Override
    protected JPanel createButtonPanel() {
        buttonPanel = new ClassButtonPanel(studentClient, inputPanel, studentSystem, user);
        return buttonPanel.getPanel();
    }
}