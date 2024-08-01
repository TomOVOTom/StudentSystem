package student_management.ui.button;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;
import student_management.ui.button.course.AddCourseButton;
import student_management.ui.button.course.QueryCourseButton;
import student_management.ui.button.course.RemoveCourseButton;
import student_management.ui.button.course.UpdateCourseButton;
import student_management.ui.button.student.AddStudentButton;
import student_management.ui.button.student.QueryStudentButton;
import student_management.ui.button.student.RemoveStudentButton;
import student_management.ui.button.student.UpdateStudentButton;

import javax.swing.*;

public class ButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public ButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField courseField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddButton() {
        return new AddStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createRemoveButton() {
        return new RemoveStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createUpdateButton() {
        return new UpdateStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createAddCourseButton() {
        return new AddCourseButton(studentClient, idField, courseField, gradeField, studentSystem).createButton();
    }

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentClient, idField, courseField, studentSystem).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentClient, idField, courseField, gradeField, studentSystem).createButton();
    }

    public JButton createQueryStudentButton() {
        return new QueryStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createQueryCourseButton() {
        return new QueryCourseButton(studentClient, idField, courseField, studentSystem).createButton();
    }
}