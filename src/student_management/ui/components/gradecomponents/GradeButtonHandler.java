package student_management.ui.components.gradecomponents;

import student_management.client.StudentClient;
import student_management.model.entity.Grade;
import student_management.model.entity.User;
import student_management.ui.main.StudentSystem;
import student_management.util.validator.CourseValidator;
import student_management.util.validator.GradeValidator;
import student_management.util.validator.StudentValidator;

import javax.swing.*;

public class GradeButtonHandler {
    private StudentClient studentClient;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField scoreField;
    private StudentSystem studentSystem;
    private User user;

    public GradeButtonHandler(StudentClient studentClient, JTextField studentIdField, JTextField courseIdField, JTextField scoreField, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentIdField = studentIdField;
        this.courseIdField = courseIdField;
        this.scoreField = scoreField;
        this.studentSystem = studentSystem;
        this.user = user;
    }

    public JButton createAddGradeButton() {
        JButton addButton = new JButton("添加成绩");
        addButton.addActionListener(e -> {
            try {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                int score = Integer.parseInt(scoreField.getText());

                StudentValidator.validateStudentId(studentId);
                CourseValidator.validateCourseId(courseId);
                GradeValidator.validateScore(score);

                Grade newGrade = new Grade(studentId, courseId, score);
                String response = studentClient.sendCommand("GRADE_ADD", user, newGrade);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(studentSystem, "成绩必须是一个整数", "输入错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(studentSystem, ex.getMessage(), "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return addButton;
    }

    public JButton createRemoveGradeButton() {
        JButton removeButton = new JButton("删除成绩");
        removeButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("GRADE_REMOVE_GRADE", user, studentId, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
            studentSystem.updateDisplay();
        });
        return removeButton;
    }

    public JButton createUpdateGradeButton() {
        JButton updateButton = new JButton("更新成绩");
        updateButton.addActionListener(e -> {
            try {
                String studentId = studentIdField.getText();
                String courseId = courseIdField.getText();
                int score = Integer.parseInt(scoreField.getText());

                StudentValidator.validateStudentId(studentId);
                CourseValidator.validateCourseId(courseId);
                GradeValidator.validateScore(score);

                Grade updatedGrade = new Grade(studentId, courseId, score);
                String response = studentClient.sendCommand("GRADE_UPDATE_GRADE", user, updatedGrade);
                JOptionPane.showMessageDialog(studentSystem, response);
                studentSystem.updateDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(studentSystem, "成绩必须是一个整数", "输入错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(studentSystem, ex.getMessage(), "输入错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        return updateButton;
    }

    public JButton createQueryGradeButton() {
        JButton queryButton = new JButton("查询成绩");
        queryButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String courseId = courseIdField.getText();
            String response = studentClient.sendCommand("GRADE_QUERY_GRADE", user, studentId, courseId);
            JOptionPane.showMessageDialog(studentSystem, response);
        });
        return queryButton;
    }

    public JButton createClearGradeFieldsButton() {
        JButton clearButton = new JButton("清空成绩输入框");
        clearButton.addActionListener(e -> {
            studentIdField.setText("");
            courseIdField.setText("");
            scoreField.setText("");
        });
        return clearButton;
    }
}