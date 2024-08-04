package student_management.model.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String courseName;
    private String teacherId;
    private String gradingSystem;
    private int grade;
    private float credits;

    public Course(String courseId, String courseName, String teacherId, String gradingSystem, float credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.gradingSystem = gradingSystem;
        this.credits = credits;
        this.grade = -1;
    }

    // 添加 getter 和 setter
    public float getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getGradingSystem() {
        return gradingSystem;
    }

    public void setGradingSystem(String gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getScoreInPercentage(int score) {
        if ("等级制(A,B,C,D,F)".equals(gradingSystem)) {
            switch (score) {
                case 'A':
                    return 100;
                case 'B':
                    return 80;
                case 'C':
                    return 70;
                case 'D':
                    return 60;
                case 'F':
                    return 0;
                default:
                    throw new IllegalArgumentException("无效的等级");
            }
        } else {
            return score; // 百分制直接返回原始分数
        }
    }

    public int getFullScore() {
        if ("等级制(A,B,C,D,F)".equals(gradingSystem)) {
            return 'A';  // 返回字符 'A' 的 ASCII 值
        } else {
            return 100;  // 百分制满分为100
        }
    }

    @Override
    public String toString() {
        return "课程编号: " + courseId + ", 课程名称: " + courseName + ", 教师ID: " + teacherId +
                ", 评分方式: " + gradingSystem + ", 学分: " + credits +
                ", 满分: " + (getFullScore() == 'A' ? "A" : getFullScore());
    }
}