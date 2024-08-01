package library_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField courseField;
    private JTextField gradeField;
    private LibrarySystem librarySystem;

    public UpdateCourseButton(StudentManager studentManager, JTextField idField, JTextField courseField, JTextField gradeField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.librarySystem = librarySystem;
    }

    public JButton createButton() {
        JButton updateCourseButton = new JButton("修改课程");
        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                int grade = Integer.parseInt(gradeField.getText());
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.updateCourse(course, grade);
                    studentManager.saveToFile();
                    librarySystem.updateDisplay();
                }
            }
        });
        return updateCourseButton;
    }
}