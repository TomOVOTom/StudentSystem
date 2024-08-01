package student_management;

import javax.swing.*;
import java.awt.*;

public class StudentSystem extends JFrame {
    private StudentManager studentManager;
    private JTextArea displayArea;

    public StudentSystem() {
        studentManager = new StudentManager();
        initComponents();
    }

    private void initComponents() {
        setTitle("学生管理系统");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        InputPanel inputPanel = new InputPanel();
        add(inputPanel.getPanel(), BorderLayout.NORTH);

        ButtonPanel buttonPanel = new ButtonPanel(studentManager, inputPanel, this);
        add(buttonPanel.getPanel(), BorderLayout.SOUTH);

        updateDisplay();
    }

    public void updateDisplay() {
        displayArea.setText("");
        for (Student student : studentManager.getStudents()) {
            displayArea.append(student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentSystem().setVisible(true);
        });
    }
}