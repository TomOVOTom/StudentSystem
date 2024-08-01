package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryCourseButton {
    private StudentClient studentClient;
    private JTextField idField;
    private JTextField courseField;
    private StudentSystem studentSystem;

    public QueryCourseButton(StudentClient studentClient, JTextField idField, JTextField courseField, StudentSystem studentSystem) {
        this.studentClient = studentClient;
        this.idField = idField;
        this.courseField = courseField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton queryCourseButton = new JButton("查询课程");
        queryCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String course = courseField.getText();
                String response = studentClient.sendCommand("QUERY_COURSE", id, course);
                JOptionPane.showMessageDialog(studentSystem, response);
            }
        });
        return queryCourseButton;
    }
}