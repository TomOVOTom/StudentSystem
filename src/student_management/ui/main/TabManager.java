package student_management.ui.main;

import student_management.client.StudentClient;
import student_management.model.entity.User;
import student_management.ui.views.classview.ClassPanel;
import student_management.ui.views.courseview.CoursePanel;
import student_management.ui.views.departmentview.DepartmentPanel;
import student_management.ui.views.gradeview.GradePanel;
import student_management.ui.views.studentcourseview.StudentCourseSelectionPanel;
import student_management.ui.views.studentview.StudentPanel;
import student_management.ui.views.teacherview.TeacherPanel;
import student_management.ui.views.userview.UserPanel;

import javax.swing.*;
import java.awt.*;

public class TabManager {
    private JTabbedPane tabbedPane;
    private StudentClient studentClient;
    private StudentSystem studentSystem;
    private User user;

    public TabManager(StudentClient studentClient, StudentSystem studentSystem, User user) {
        this.studentClient = studentClient;
        this.studentSystem = studentSystem;
        this.user = user;
        initTabbedPane();
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("学生管理", new StudentPanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("教师管理", new TeacherPanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("班级管理", new ClassPanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("院系管理", new DepartmentPanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("课程管理", new CoursePanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("成绩管理", new GradePanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("用户管理", new UserPanel(studentClient, studentSystem, user).getPanel());
        tabbedPane.addTab("学生选课管理", new StudentCourseSelectionPanel(studentClient, studentSystem, user).getPanel());
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public int getSelectedIndex() {
        return tabbedPane.getSelectedIndex();
    }

    public String getSelectedTabTitle() {
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }

    public void updateTabDisplay(int tabIndex, String result) {
        Component component = tabbedPane.getComponentAt(tabIndex);
        if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            for (Component c : panel.getComponents()) {
                if (c instanceof JTextArea) {
                    ((JTextArea) c).setText(result);
                    break;
                }
            }
        }
    }
}