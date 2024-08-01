package student_management;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private String id;
    private String name;
    private int age;
    private Map<String, Integer> courses; // 课程和成绩

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new HashMap<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Map<String, Integer> getCourses() { return courses; }

    public void addCourse(String course, int grade) {
        courses.put(course, grade);
    }

    public void removeCourse(String course) {
        courses.remove(course);
    }

    public void updateCourse(String course, int grade) {
        courses.put(course, grade);
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', age=" + age + ", courses=" + courses + "}";
    }
}