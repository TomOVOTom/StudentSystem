package student_management.ui.layout;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public abstract class CommonPanelLayout {
    protected JPanel panel;
    protected StudentClient studentClient;
    protected StudentSystem studentSystem;
    protected User user;
    protected JPanel inputPanel;
    protected JPanel buttonPanel;

    public CommonPanelLayout(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

    protected void initComponents() {
        panel = new JPanel(new BorderLayout());

        inputPanel = createInputPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        buttonPanel = createButtonPanel();
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    protected abstract JPanel createInputPanel();
    protected abstract JPanel createButtonPanel();

    public JPanel getPanel() {
        return panel;
    }
}