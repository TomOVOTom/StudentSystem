package student_management.ui.views.teacherview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.CommonPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class TeacherPanel extends CommonPanelLayout {
    private TeacherInputPanel inputPanel;
    private TeacherButtonPanel buttonPanel;

    public TeacherPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        super(studentClient, studentSystem, user);
    }

    @Override
    protected JPanel createInputPanel() {
        inputPanel = new TeacherInputPanel();
        return inputPanel.getPanel();
    }

    @Override
    protected JPanel createButtonPanel() {
        buttonPanel = new TeacherButtonPanel(studentClient, inputPanel, studentSystem, user);
        return buttonPanel.getPanel();
    }
}