package student_management;

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
        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            Student student = new Student(id, name, age);
            String response = studentClient.sendCommand("ADD_STUDENT", student);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return addButton;
    }

    public JButton createRemoveButton() {
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(e -> {
            String id = idField.getText();
            String response = studentClient.sendCommand("REMOVE_STUDENT", id);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateButton() {
        return new UpdateButton(studentClient, idField, nameField, ageField, studentSystem).createButton();
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
        JButton queryStudentButton = new JButton("查询学生");
        queryStudentButton.addActionListener(e -> {
            String id = idField.getText();
            String response = studentClient.sendCommand("QUERY_STUDENT", id);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryStudentButton;
    }

    public JButton createQueryCourseButton() {
        return new QueryCourseButton(studentClient, idField, courseField, studentSystem).createButton();
    }
}