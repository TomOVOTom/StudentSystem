package student_management.model.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int age;
    private String classId;
    private String departmentId;
    private Map<String, Course> courses; // 课程和课程信息

    public Student(String id, String name, int age, String classId, String departmentId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classId = classId;
        this.departmentId = departmentId;
        this.courses = new HashMap<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.put(course.getCourseId(), course);
    }

    public void addCourse(String courseId, String courseName, String teacher, int grade) {
        Course course = new Course(courseId, courseName, teacher, grade);
        this.courses.put(courseId, course);
    }

    public void removeCourse(String courseId) {
        courses.remove(courseId);
    }

    public void updateCourse(String courseId, String courseName, String teacher, int grade) {
        courses.put(courseId, new Course(courseId, courseName, teacher, grade));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("学号: ").append(id)
                .append(", 姓名: ").append(name)
                .append(", 年龄: ").append(age)
                .append(", 班级: ").append(classId)
                .append(", 院系: ").append(departmentId)
                .append(", 课程: ");
        for (Course course : courses.values()) {
            sb.append(course.toString()).append(", ");
        }
        return sb.toString();
    }
}