package student_management.ui.views.studentcourseview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class StudentCourseSelectionPanel {
    private JPanel panel;
    private StudentClient studentClient;
    private StudentSystem studentSystem;
    private User user;
    private StudentCourseInputPanel inputPanel;
    private StudentCourseButtonPanel buttonPanel;

    public StudentCourseSelectionPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());

        inputPanel = new StudentCourseInputPanel();
        panel.add(inputPanel.getPanel(), BorderLayout.NORTH);

        buttonPanel = new StudentCourseButtonPanel(studentClient, inputPanel, studentSystem, user);
        panel.add(buttonPanel.getPanel(), BorderLayout.CENTER);


    }

    public JPanel getPanel() {
        return panel;
    }

}