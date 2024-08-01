package student_management;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel {
    private JPanel panel;

    public ButtonPanel(StudentManager studentManager, InputPanel inputPanel, StudentSystem studentSystem) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));

        ButtonHandler buttonHandler = new ButtonHandler(studentManager, inputPanel.getIdField(), inputPanel.getNameField(), inputPanel.getAgeField(), inputPanel.getCourseField(), inputPanel.getGradeField(), studentSystem);

        panel.add(buttonHandler.createAddButton());
        panel.add(buttonHandler.createRemoveButton());
        panel.add(buttonHandler.createUpdateButton());
        panel.add(buttonHandler.createAddCourseButton());
        panel.add(buttonHandler.createRemoveCourseButton());
        panel.add(buttonHandler.createUpdateCourseButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}