package student_management.ui.main;

import student_management.client.StudentClient;
import student_management.model.entity.User;

import javax.swing.*;

public class DisplayUpdater {
    private StudentClient studentClient;
    private User user;

    public DisplayUpdater(StudentClient studentClient, User user) {
        this.studentClient = studentClient;
        this.user = user;
    }

    public String getUpdateResult(String tabTitle) {
        try {
            switch (tabTitle) {
                case "学生管理":
                    return studentClient.sendCommand("STUDENT_QUERY_ALL_STUDENTS", user);
                case "班级管理":
                    return studentClient.sendCommand("CLASS_QUERY_ALL_CLASSES", user);
                case "课程管理":
                    return studentClient.sendCommand("COURSE_QUERY_ALL_COURSES", user);
                case "教师管理":
                    return studentClient.sendCommand("TEACHER_QUERY_ALL_TEACHERS", user);
                case "成绩管理":
                    return studentClient.sendCommand("GRADE_QUERY_ALL_GRADES", user);
                case "院系管理":
                    return studentClient.sendCommand("DEPARTMENT_QUERY_ALL_DEPARTMENTS", user);
                case "用户管理":
                    // 移除查询所有用户的功能
                    return "用户管理功能已更新，不再支持查询所有用户。";
                default:
                    return "未知标签页: " + tabTitle;
            }
        } catch (Exception e) {
            String errorMessage = "更新显示时出错: " + e.getMessage();
            System.err.println(errorMessage);
            JOptionPane.showMessageDialog(null, "无法获取信息，请检查服务器连接。\n错误详情: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return "错误: " + errorMessage;
        }
    }
}