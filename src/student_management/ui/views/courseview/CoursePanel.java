package student_management.ui.views.courseview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class CoursePanel {
    private JPanel panel;
    private StudentClient studentClient;
    private StudentSystem studentSystem;
    private User user;
    private CourseInputPanel inputPanel;
    private CourseButtonPanel buttonPanel;
    private JTextArea displayArea;

    public CoursePanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());

        inputPanel = new CourseInputPanel();
        panel.add(inputPanel.getPanel(), BorderLayout.NORTH);

        buttonPanel = new CourseButtonPanel(studentClient, inputPanel, studentSystem, user);
        panel.add(buttonPanel.getPanel(), BorderLayout.CENTER);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateDisplay(String result) {
        displayArea.setText(result);
    }
}