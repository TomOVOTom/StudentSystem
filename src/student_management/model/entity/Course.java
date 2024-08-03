package student_management.model.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String courseName;
    private String teacher;
    private String gradingSystem;
    private int grade;

    public Course(String courseId, String courseName, String teacher, String gradingSystem) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.gradingSystem = gradingSystem;
        this.grade = -1; // 初始化为-1，表示还未设置成绩
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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

    @Override
    public String toString() {
        return "课程编号: " + courseId + ", 课程名称: " + courseName + ", 教师: " + teacher +
                ", 评分方式: " + gradingSystem + ", 成绩: " + (grade == -1 ? "未设置" : grade);
    }
}