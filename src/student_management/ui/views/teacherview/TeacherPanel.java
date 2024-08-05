package student_management.ui.views.teacherview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.CommonPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

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
    JPanel panel = buttonPanel.getPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets(0, 0, 0, 0);

       Component[] components = panel.getComponents();
    int cols = 3;
    for (int i = 0; i < components.length; i++) {
        gbc.gridx = i % cols;
        gbc.gridy = i / cols;
        if (components[i] instanceof JButton) {
            JButton button = (JButton) components[i];
            button.setPreferredSize(new Dimension(180, 40));
        }
        panel.add(components[i], gbc);
    }
    return panel;
}
}