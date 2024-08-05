package student_management.ui.views.gradeview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class GradePanel {
    private JPanel panel;
    private StudentClient studentClient;
    private StudentSystem studentSystem;
    private User user;
    private GradeInputPanel inputPanel;
    private GradeButtonPanel buttonPanel;


    public GradePanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());

        inputPanel = new GradeInputPanel();
        panel.add(inputPanel.getPanel(), BorderLayout.NORTH);

        buttonPanel = new GradeButtonPanel(studentClient, inputPanel, studentSystem, user);
        panel.add(buttonPanel.getPanel(), BorderLayout.CENTER);

    }

    public JPanel getPanel() {
        return panel;
    }

}