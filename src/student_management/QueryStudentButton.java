package student_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryStudentButton {
    private StudentManager studentManager;
    private JTextField idField;
    private StudentSystem studentSystem;

    public QueryStudentButton(StudentManager studentManager, JTextField idField, StudentSystem studentSystem) {
        this.studentManager = studentManager;
        this.idField = idField;
        this.studentSystem = studentSystem;
    }

    public JButton createButton() {
        JButton queryStudentButton = new JButton("查询学生");
        queryStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String result = studentManager.queryStudent(id);
                JOptionPane.showMessageDialog(studentSystem, result);
            }
        });
        return queryStudentButton;
    }
}