package library_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCourseButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField courseField;
    private LibrarySystem librarySystem;

    public RemoveCourseButton(StudentManager studentManager, JTextField idField, JTextField courseField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.courseField = courseField;
        this.librarySystem = librarySystem;
    }

    public JButton createButton() {
        JButton removeCourseButton = new JButton("删除课程");
        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.removeCourse(course);
                    studentManager.saveToFile();
                    librarySystem.updateDisplay();
                }
            }
        });
        return removeCourseButton;
    }
}