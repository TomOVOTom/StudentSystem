package student_management.model.entity;

import java.io.Serializable;

public class Class implements Serializable {
    private static final long serialVersionUID = 1L;

    private String classId;
    private String className;
    private String department;

    public Class(String classId, String className, String department) {
        this.classId = classId;
        this.className = className;
        this.department = department;
    }

    // Getters and Setters
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "班级编号: " + classId + ", 班级名称: " + className + ", 院系: " + department;
    }
}