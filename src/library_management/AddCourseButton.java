package library_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField courseField;
    private JTextField gradeField;
    private LibrarySystem librarySystem;

    public AddCourseButton(StudentManager studentManager, JTextField idField, JTextField courseField, JTextField gradeField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.librarySystem = librarySystem;
    }

    public JButton createButton() {
        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                int grade = Integer.parseInt(gradeField.getText());
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.addCourse(course, grade);
                    studentManager.saveToFile();
                    librarySystem.updateDisplay();
                }
            }
        });
        return addCourseButton;
    }
}