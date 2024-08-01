package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourseButton {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField courseField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public AddCourseButton(StudentManager studentManager, JTextField idField, JTextField courseField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
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
                    studentSystem.updateDisplay();
                }
            }
        });
        return addCourseButton;
    }
}