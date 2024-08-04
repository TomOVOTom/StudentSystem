package student_management.server.handlers;

import student_management.model.entity.Course;
import student_management.model.entity.User;
import student_management.model.manager.CourseManager;
import student_management.model.manager.TeacherManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

import static student_management.util.validator.CourseValidator.validateTeacherId;

public class CourseHandler {
    private CourseManager courseManager;
    private Logger logger;
    private TeacherManager teacherManager;

    public CourseHandler(CourseManager courseManager, TeacherManager teacherManager, Logger logger) {
        this.courseManager = courseManager;
        this.teacherManager = teacherManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "COURSE_ADD_COURSE":
                    return handleAddCourse(ois, user);
                case "COURSE_REMOVE_COURSE":
                    return handleRemoveCourse(ois, user);
                case "COURSE_UPDATE_COURSE":
                    return handleUpdateCourse(ois, user);
                case "COURSE_QUERY_COURSE":
                    return handleQueryCourse(ois, user);
                case "COURSE_QUERY_ALL_COURSES":
                    return handleQueryAllCourses(user);
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

    private String handleAddCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String courseId = (String) ois.readObject();
        String courseName = (String) ois.readObject();
        String gradingSystem = (String) ois.readObject();
        String teacherId = (String) ois.readObject();
        float credits = (float) ois.readObject();
        validateTeacherId(teacherId, teacherManager);
        Course course = new Course(courseId, courseName, teacherId, gradingSystem, credits);
        courseManager.addCourse(course, user);
        logger.log("用户 " + user.getUsername() + " 添加了课程: " + course.getCourseId());
        return "课程添加成功";
    }

    private String handleRemoveCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String courseId = (String) ois.readObject();
        courseManager.removeCourse(courseId, user);
        logger.log("用户 " + user.getUsername() + " 删除了课程: " + courseId);
        return "课程删除成功";
    }

    private String handleUpdateCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String courseId = (String) ois.readObject();
        String courseName = (String) ois.readObject();
        String teacherId = (String) ois.readObject();
        String gradingSystem = (String) ois.readObject();
        float credits = (float) ois.readObject();
        Course course = new Course(courseId, courseName, teacherId, gradingSystem, credits);
        courseManager.updateCourse(course, user);
        logger.log("用户 " + user.getUsername() + " 更新了课程: " + courseId);
        return "课程更新成功";
    }

    private String handleQueryCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String courseId = (String) ois.readObject();
        logger.log("用户 " + user.getUsername() + " 查询了课程: " + courseId);
        return courseManager.queryCourse(courseId);
    }

    private String handleQueryAllCourses(User user) {
        logger.log("用户 " + user.getUsername() + " 查询了所有课程");
        return courseManager.queryAllCourses();
    }
}