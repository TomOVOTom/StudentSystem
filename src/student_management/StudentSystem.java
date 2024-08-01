package student_management;

import javax.swing.*;
import java.awt.*;

public class StudentSystem extends JFrame {
    private StudentClient studentClient;
    private JTextArea displayArea;

    public StudentSystem() {
        studentClient = new StudentClient("localhost", 12345);
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

        ButtonPanel buttonPanel = new ButtonPanel(studentClient, inputPanel, this);
        add(buttonPanel.getPanel(), BorderLayout.SOUTH);

        updateDisplay();
    }

    public void updateDisplay() {
        displayArea.setText(studentClient.sendCommand("QUERY_ALL_STUDENTS"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentSystem().setVisible(true);
        });
    }
}