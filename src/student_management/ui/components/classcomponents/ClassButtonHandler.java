package student_management.ui.components.classcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class ClassButtonHandler {
    private StudentClient studentClient;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentField;
    private StudentSystem studentSystem;
    private User user;

    public ClassButtonHandler(StudentClient studentClient, JTextField classIdField,
                              JTextField classNameField, JTextField departmentField,
                              StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentField = departmentField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddStudentClassButton() {
        return new AddClassButton(studentClient, classIdField, classNameField, departmentField, studentSystem, user).createButton();
    }

    public JButton createRemoveStudentClassButton() {
        return new RemoveClassButton(studentClient, classIdField, studentSystem, user).createButton();
    }

    public JButton createUpdateStudentClassButton() {
        return new UpdateClassButton(studentClient, classIdField, classNameField, departmentField, studentSystem, user).createButton();
    }

    public JButton createQueryStudentClassButton() {
        return new QueryClassButton(studentClient, classIdField, studentSystem, user).createButton();
    }

    public JButton createClearStudentClassFieldsButton() {
        JButton clearButton = new JButton("清空班级输入框");
        clearButton.addActionListener(e -> {
            classIdField.setText("");
            classNameField.setText("");
            departmentField.setText("");
        });
        return clearButton;
    }
}