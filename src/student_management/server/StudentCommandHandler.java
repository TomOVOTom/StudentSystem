package student_management.server;

import student_management.model.Student;
import student_management.model.StudentManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class StudentCommandHandler {
    private StudentManager studentManager;

    public StudentCommandHandler(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (command) {
            case "STUDENT_ADD_STUDENT": {
                Student student = (Student) ois.readObject();
                studentManager.addStudent(student);
                return "学生添加成功";
            }
            case "STUDENT_REMOVE_STUDENT": {
                String id = (String) ois.readObject();
                studentManager.removeStudent(id);
                return "学生删除成功";
            }
            case "STUDENT_UPDATE_STUDENT": {
                String id = (String) ois.readObject();
                String name = (String) ois.readObject();
                int age = (int) ois.readObject();
                studentManager.updateStudent(id, name, age);
                return "学生更新成功";
            }
            case "STUDENT_ADD_COURSE": {
                String id = (String) ois.readObject();
                String courseId = (String) ois.readObject();
                String courseName = (String) ois.readObject();
                String teacher = (String) ois.readObject();
                int grade = (int) ois.readObject();
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.addCourse(courseId, courseName, teacher, grade);
                    studentManager.saveStudentsToFile();
                    return "课程添加成功";
                } else {
                    return "学生未找到";
                }
            }
            case "STUDENT_REMOVE_COURSE": {
                String id = (String) ois.readObject();
                String courseId = (String) ois.readObject();
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.removeCourse(courseId);
                    studentManager.saveStudentsToFile();
                    return "课程删除成功";
                } else {
                    return "学生未找到";
                }
            }
            case "STUDENT_UPDATE_COURSE": {
                String id = (String) ois.readObject();
                String courseId = (String) ois.readObject();
                String courseName = (String) ois.readObject();
                String teacher = (String) ois.readObject();
                int grade = (int) ois.readObject();
                Student student = studentManager.getStudent(id);
                if (student != null) {
                    student.updateCourse(courseId, courseName, teacher, grade);
                    studentManager.saveStudentsToFile();
                    return "课程更新成功";
                } else {
                    return "学生未找到";
                }
            }
            case "STUDENT_QUERY_STUDENT": {
                String id = (String) ois.readObject();
                return studentManager.queryStudent(id);
            }
            case "STUDENT_QUERY_COURSE": {
                String id = (String) ois.readObject();
                String courseId = (String) ois.readObject();
                return studentManager.queryCourse(id, courseId);
            }
            case "STUDENT_QUERY_ALL_STUDENTS": {
                return queryAllStudents();
            }
            default:
                return "未知命令";
        }
    }

    private String queryAllStudents() {
        StringBuilder result = new StringBuilder();
        for (Student student : studentManager.getStudents()) {
            result.append(student.toString()).append("\n");
        }
        return result.toString();
    }
}