package student_management.ui.views.departmentview;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.layout.AbstractPanelLayout;
import student_management.ui.main.StudentSystem;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends AbstractPanelLayout {

    private DepartmentInputPanel inputPanel;
    private DepartmentButtonPanel buttonPanel;

    public DepartmentPanel(StudentClient studentClient, StudentSystem studentSystem, User user) {
       super(studentClient, studentSystem, user);
    }

    @Override
    protected JPanel createInputPanel() {
       inputPanel = new DepartmentInputPanel();
       return inputPanel.getPanel();
    }

    @Override
    protected JPanel createButtonPanel() {
       buttonPanel = new DepartmentButtonPanel(studentClient, inputPanel, studentSystem, user);
       return buttonPanel.getPanel();
    }

}