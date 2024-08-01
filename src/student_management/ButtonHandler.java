package student_management;

import javax.swing.*;

public class ButtonHandler {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField gradeField;
    private StudentSystem studentSystem;

    public ButtonHandler(StudentManager studentManager, JTextField idField, JTextField nameField, JTextField ageField, JTextField courseField, JTextField gradeField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.studentSystem = studentSystem;
    }

    public JButton createAddButton() {
        return new AddButton(studentManager, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createRemoveButton() {
        return new RemoveButton(studentManager, idField, studentSystem).createButton();
    }

    public JButton createUpdateButton() {
        return new UpdateButton(studentManager, idField, nameField, ageField, studentSystem).createButton();
    }

    public JButton createAddCourseButton() {
        return new AddCourseButton(studentManager, idField, courseField, gradeField, studentSystem).createButton();
    }

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentManager, idField, courseField, studentSystem).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentManager, idField, courseField, gradeField, studentSystem).createButton();
    }
}