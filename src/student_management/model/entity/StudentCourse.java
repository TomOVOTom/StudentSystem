package student_management.model.entity;

import java.io.Serializable;

public class StudentCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String courseId;

    public StudentCourse(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}