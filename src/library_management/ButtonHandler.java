package library_management;

import javax.swing.*;

public class ButtonHandler {
    private StudentManager studentManager;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField gradeField;
    private LibrarySystem librarySystem;

    public ButtonHandler(StudentManager studentManager, JTextField idField, JTextField nameField, JTextField ageField, JTextField courseField, JTextField gradeField, LibrarySystem librarySystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.courseField = courseField;
        this.gradeField = gradeField;
        this.librarySystem = librarySystem;
    }

    public JButton createAddButton() {
        return new AddButton(studentManager, idField, nameField, ageField, librarySystem).createButton();
    }

    public JButton createRemoveButton() {
        return new RemoveButton(studentManager, idField, librarySystem).createButton();
    }

    public JButton createUpdateButton() {
        return new UpdateButton(studentManager, idField, nameField, ageField, librarySystem).createButton();
    }

    public JButton createAddCourseButton() {
        return new AddCourseButton(studentManager, idField, courseField, gradeField, librarySystem).createButton();
    }

    public JButton createRemoveCourseButton() {
        return new RemoveCourseButton(studentManager, idField, courseField, librarySystem).createButton();
    }

    public JButton createUpdateCourseButton() {
        return new UpdateCourseButton(studentManager, idField, courseField, gradeField, librarySystem).createButton();
    }
}