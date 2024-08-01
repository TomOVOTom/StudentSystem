package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private StudentSystem studentSystem;

    public UpdateButton(StudentManager studentManager, JTextField idField, JTextField nameField, JTextField ageField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton updateButton = new JButton("修改学生");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                studentManager.updateStudent(id, name, age);
                studentSystem.updateDisplay();
            }
        });
        return updateButton;
    }
}