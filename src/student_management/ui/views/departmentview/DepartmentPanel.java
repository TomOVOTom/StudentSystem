package student_management.ui.views.departmentview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel {
    private JPanel panel;
    private StudentClient studentClient;
    private StudentSystem studentSystem;
    private User user;
    private DepartmentInputPanel inputPanel;
    private DepartmentButtonPanel buttonPanel;

    public DepartmentPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());

        inputPanel = new DepartmentInputPanel();
        panel.add(inputPanel.getPanel(), BorderLayout.NORTH);

        buttonPanel = new DepartmentButtonPanel(studentClient, inputPanel, studentSystem, user);
        panel.add(buttonPanel.getPanel(), BorderLayout.CENTER);

    }

    public JPanel getPanel() {
        return panel;
    }

}