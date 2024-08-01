package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButton {
    private StudentManager studentManager;
    private JTextField idField;
    private StudentSystem studentSystem;

    public RemoveButton(StudentManager studentManager, JTextField idField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                studentManager.removeStudent(id);
                studentSystem.updateDisplay();
            }
        });
        return removeButton;
    }
}