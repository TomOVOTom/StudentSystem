package library_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButton {
    private StudentManager studentManager;
    private JTextField idField;
    private LibrarySystem librarySystem;

    public RemoveButton(StudentManager studentManager, JTextField idField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.librarySystem = librarySystem;
    }

    public JButton createButton() {
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                studentManager.removeStudent(id);
                librarySystem.updateDisplay();
            }
        });
        return removeButton;
    }
}