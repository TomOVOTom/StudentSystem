package library_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private LibrarySystem librarySystem;

    public AddButton(StudentManager studentManager, JTextField idField, JTextField nameField, JTextField ageField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.librarySystem = librarySystem;
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
                librarySystem.updateDisplay();
            }
        });
        return addButton;
    }
}