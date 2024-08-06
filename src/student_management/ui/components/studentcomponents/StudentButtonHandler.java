package student_management.ui.components.studentcomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Student;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;

import javax.swing.*;

public class StudentButtonHandler {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private StudentSystem studentSystem;
    private User user;

public StudentButtonHandler(StudentClient studentClient, JTextField idField, JTextField nameField, JTextField ageField, JComboBox<String> genderComboBox, JTextField classIdField, JTextField classNameField, JTextField departmentIdField, JTextField departmentNameField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.nameField = nameField;
        this.ageField = ageField;
        this.genderComboBox = genderComboBox;
        this.classIdField = classIdField;
        this.classNameField = classNameField;
        this.departmentIdField = departmentIdField;
        this.departmentNameField = departmentNameField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

     public JButton createAddStudentButton() {
        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(e -> {
            try {
                Student student = createStudentFromFields();
                String response = studentClient.sendCommand("STUDENT_ADD_STUDENT", user, student);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(studentSystem, "添加学生失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return addButton;
    }

    public JButton createRemoveStudentButton() {
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(e -> {
            String id = idField.getText();
            String response = studentClient.sendCommand("STUDENT_REMOVE_STUDENT", user, id);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateStudentButton() {
        JButton updateButton = new JButton("更新学生");
        updateButton.addActionListener(e -> {
            try {
                Student student = createStudentFromFields();
                String response = studentClient.sendCommand("STUDENT_UPDATE_STUDENT", user, student);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(studentSystem, "更新学生失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return updateButton;
    }

    public JButton createQueryStudentButton() {
        JButton queryButton = new JButton("查询学生");
        queryButton.addActionListener(e -> {
            String id = idField.getText();
            String response = studentClient.sendCommand("STUDENT_QUERY_STUDENT", user, id);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }

    private Student createStudentFromFields() {
        String id = idField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = (String) genderComboBox.getSelectedItem();
        String classId = classIdField.getText();
        String className = classNameField.getText();
        String departmentId = departmentIdField.getText();
        String departmentName = departmentNameField.getText();
        return new Student(id, name, age, gender, classId, className, departmentId, departmentName);
    }

    public JButton createClearStudentFieldsButton() {
        JButton clearButton = new JButton("清空学生输入框");
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            genderComboBox.setSelectedIndex(0);
            classIdField.setText("");
            classNameField.setText("");
            departmentIdField.setText("");
            departmentNameField.setText("");
        });
        return clearButton;
    }
}