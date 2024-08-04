package student_management.server.handlers;

import student_management.model.entity.Grade;
import student_management.model.entity.User;
import student_management.model.manager.GradeManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GradeHandler {
    private GradeManager gradeManager;
    private Logger logger;

    public GradeHandler(GradeManager gradeManager, Logger logger) {
        this.gradeManager = gradeManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "GRADE_ADD":
                    return addGrade(ois, user);
                case "GRADE_REMOVE_GRADE":
                    return removeGrade(ois, user);
                case "GRADE_UPDATE_GRADE":
                    return updateGrade(ois, user);
                case "GRADE_QUERY_GRADE":
                    return queryGrade(ois, user);
                case "GRADE_QUERY_ALL_GRADES":
                    return queryAllGrades(user);
                default:
                    return "未知的成绩命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

    private String addGrade(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Grade grade = (Grade) ois.readObject();
        gradeManager.addGrade(grade);
        logger.log("用户 " + user.getUsername() + " 添加了成绩: " + grade.toString());
        return "成绩添加成功";
    }

    private String removeGrade(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String studentId = (String) ois.readObject();
        String courseId = (String) ois.readObject();
        gradeManager.removeGrade(studentId, courseId);
        return "成绩删除成功";
    }

    private String updateGrade(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        Grade grade = (Grade) ois.readObject();
        gradeManager.updateGrade(grade);
        return "成绩更新成功";
    }

    private String queryGrade(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String studentId = (String) ois.readObject();
        String courseId = (String) ois.readObject();
        Grade grade = gradeManager.getGrade(studentId, courseId);
        return grade != null ? grade.toString() : "成绩不存在";
    }

    private String queryAllGrades(User user) {
        StringBuilder result = new StringBuilder();
        for (Grade grade : gradeManager.getAllGrades()) {
            result.append(grade.toString()).append("\n");
        }
        return result.toString();
    }
}