package student_management.model.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int age;
    private String gender;
    private String classId;
    private String className;
    private String departmentId;
    private String departmentName;

    public Student(String id, String name, int age, String gender, String classId, String className, String departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.classId = classId;
        this.className = className;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "学号: " + id +
                ", 姓名: " + name +
                ", 年龄: " + age +
                ", 性别: " + gender +
                ", 班级ID: " + classId +
                ", 班级: " + className +
                ", 院系ID: " + departmentId +
                ", 院系: " + departmentName;
    }
}