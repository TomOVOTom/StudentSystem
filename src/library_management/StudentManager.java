package library_management;

import java.io.*;
import java.util.LinkedList;

public class StudentManager {
    private LinkedList<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = new LinkedList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        saveToFile();
    }

    public void updateStudent(String id, String name, int age) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setAge(age);
                break;
            }
        }
        saveToFile();
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (LinkedList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}