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
import student_management.ui.button.teacher.AddTeacherButton;
import student_management.ui.button.teacher.QueryTeacherButton;
import student_management.ui.button.teacher.RemoveTeacherButton;
import student_management.ui.button.teacher.UpdateTeacherButton;

import javax.swing.*;

public class ButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField teacherField;
    private JTextField gradeField;
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField teacherSubjectField;
    private StudentSystem studentSystem;

    public ButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JTextField courseIdField, JTextField courseNameField, JTextField teacherField, JTextField gradeField, JTextField teacherIdField, JTextField teacherNameField, JTextField teacherSubjectField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.courseIdField = courseIdField;
        this.courseNameField = courseNameField;
        this.teacherField = teacherField;
        this.gradeField = gradeField;
        this.teacherIdField = teacherIdField;
        this.teacherNameField = teacherNameField;
        this.teacherSubjectField = teacherSubjectField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddStudentButton() {
        return new AddStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createRemoveStudentButton() {
        return new RemoveStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createUpdateStudentButton() {
        return new UpdateStudentButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createAddCourseButton() {
        return new AddCourseButton(studentClient, idField, courseIdField, courseNameField, teacherField, gradeField, studentSystem).createButton();
    }

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentClient, idField, courseIdField, studentSystem).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentClient, idField, courseIdField, courseNameField, teacherField, gradeField, studentSystem).createButton();
    }

    public JButton createQueryStudentButton() {
        return new QueryStudentButton(studentClient, idField, studentSystem).createButton();
    }

    public JButton createQueryCourseButton() {
        return new QueryCourseButton(studentClient, idField, courseIdField, studentSystem).createButton();
    }

    public JButton createAddTeacherButton() {
        return new AddTeacherButton(studentClient, teacherIdField, teacherNameField, teacherSubjectField, studentSystem).createButton();
    }

    public JButton createRemoveTeacherButton() {
        return new RemoveTeacherButton(studentClient, teacherIdField, studentSystem).createButton();
    }

    public JButton createUpdateTeacherButton() {
        return new UpdateTeacherButton(studentClient, teacherIdField, teacherNameField, teacherSubjectField, studentSystem).createButton();
    }

    public JButton createQueryTeacherButton() {
        return new QueryTeacherButton(studentClient, teacherIdField, studentSystem).createButton();
    }
}