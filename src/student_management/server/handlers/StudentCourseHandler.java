package student_management.server.handlers;

import student_management.model.entity.StudentCourse;
import student_management.model.entity.User;
import student_management.model.manager.StudentCourseManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class StudentCourseHandler {
    private StudentCourseManager studentCourseManager;
    private Logger logger;

    public StudentCourseHandler(StudentCourseManager studentCourseManager, Logger logger) {
        this.studentCourseManager = studentCourseManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "STUDENT_COURSE_SELECT":
                    return handleSelectCourse(ois, user);
                case "STUDENT_COURSE_DROP":
                    return handleDropCourse(ois, user);
                case "STUDENT_COURSE_QUERY":
                    return handleQueryStudentCourses(ois, user);
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

    private String handleSelectCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String studentId = (String) ois.readObject();
        String courseId = (String) ois.readObject();
        StudentCourse studentCourse = new StudentCourse(studentId, courseId);
        studentCourseManager.selectCourse(studentCourse, user);
        return "选课成功";
    }

    private String handleDropCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String studentId = (String) ois.readObject();
        String courseId = (String) ois.readObject();
        studentCourseManager.dropCourse(studentId, courseId, user);
        return "退课成功";
    }

    private String handleQueryStudentCourses(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String studentId = (String) ois.readObject();
        List<StudentCourse> courses = studentCourseManager.getStudentCourses(studentId);
        return courses.isEmpty() ? "该学生没有选择任何课程" : courses.toString();
    }
}