package student_management.model.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String courseName;
    private String teacher;
    private int grade;

    public Course(String courseId, String courseName, String teacher, int grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.grade = grade;
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "课程编号: " + courseId + ", 课程名称: " + courseName + ", 教师: " + teacher + ", 成绩: " + grade;
    }
}