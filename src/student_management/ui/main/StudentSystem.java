package student_management.ui.main;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.core.DisplayManager;
import student_management.ui.core.SystemInitializer;

import javax.swing.*;
import java.awt.*;

public class StudentSystem extends JFrame {
    private StudentClient studentClient;
    private User user;
    private TabManager tabManager;
    private DisplayManager displayManager;

    public StudentSystem(User user) {
        System.out.println("创建 StudentSystem 实例，用户：" + user.getUsername());
        SystemInitializer.initialize(this, user);
    }

    public void initComponents() {
        System.out.println("初始化组件");
        setTitle("学生管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        tabManager = new TabManager(studentClient, this, user);
        add(tabManager.getTabbedPane(), BorderLayout.CENTER);
        displayManager = new DisplayManager(this, studentClient, user);
        System.out.println("组件初始化完成");
    }

    public void updateDisplay() {
        displayManager.updateDisplay(tabManager);
    }

    public void setStudentClient(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setDisplayUpdater(DisplayUpdater displayUpdater) {
        // This method is no longer needed, but kept for compatibility
    }
}