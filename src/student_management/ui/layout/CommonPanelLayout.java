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
    protected JTextArea displayArea;

    public CommonPanelLayout(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initComponents();
    }

   protected void initComponents() {
    panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.weightx = 1.0;

    inputPanel = createInputPanel();
    gbc.gridy = 0;
    gbc.weighty = 0;
    panel.add(inputPanel, gbc);

    buttonPanel = createButtonPanel();
    gbc.gridy = 1;
    gbc.weighty = 1.0;
    panel.add(buttonPanel, gbc);

    displayArea = new JTextArea(5, 40);
    displayArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(displayArea);
    gbc.gridy = 0;
    gbc.weighty = 0;
    panel.add(scrollPane, gbc);
}
    protected abstract JPanel createInputPanel();
    protected abstract JPanel createButtonPanel();

    public JPanel getPanel() {
        return panel;
    }

    public void updateDisplay(String result) {
        displayArea.setText(result);
    }
}