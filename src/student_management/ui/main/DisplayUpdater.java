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
                // 添加其他管理功能的查询命令
                default:
                    return "未知标签页";
            }
        } catch (Exception e) {
            System.err.println("更新显示时出错: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "无法获取信息，请检查服务器连接。", "错误", JOptionPane.ERROR_MESSAGE);
            return "错误: " + e.getMessage();
        }
    }
}