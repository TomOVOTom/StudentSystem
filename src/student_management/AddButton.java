package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private StudentSystem studentSystem;

    public AddButton(StudentManager studentManager, JTextField idField, JTextField nameField, JTextField ageField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                studentManager.addStudent(new Student(id, name, age));
                studentSystem.updateDisplay();
            }
        });
        return addButton;
    }
}