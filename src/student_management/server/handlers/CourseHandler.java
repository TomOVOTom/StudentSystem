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
        logger.log("处理课程命令: " + command + ", 用户: " + user.getUsername());
        try {
            switch (command) {
                case "COURSE_ADD":
                    return handleAddCourse(ois, user);
                case "COURSE_REMOVE":
                    return handleRemoveCourse(ois, user);
                case "COURSE_UPDATE":
                    return handleUpdateCourse(ois, user);
                case "COURSE_QUERY":
                    return handleQueryCourse(ois, user);
                case "COURSE_QUERY_ALL_COURSES":
                    return handleQueryAllCourses(user);
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            e.printStackTrace(); // 添加这行以打印完整的堆栈跟踪
            return "操作失败: " + e.getMessage();
        }
    }

    private String handleAddCourse(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
    logger.log("开始添加课程");
    try {
        if (!user.getRole().equals("admin")) {
            logger.log("用户无权限添加课程");
            return "无权限操作";
        }
        logger.log("开始读取课程信息");
        String courseId = (String) ois.readObject();
        logger.log("读取课程ID: " + courseId);
        String courseName = (String) ois.readObject();
        logger.log("读取课程名称: " + courseName);
        String gradingSystem = (String) ois.readObject();
        logger.log("读取评分系统: " + gradingSystem);
        String teacherId = (String) ois.readObject();
        logger.log("读取教师ID: " + teacherId);
        float credits;
        try {
            credits = Float.parseFloat((String) ois.readObject());
            logger.log("读取学分: " + credits);
        } catch (Exception e) {
            logger.log("读取学分失败: " + e.getMessage());
            return "添加课程失败: 无效的学分值";
        }


        logger.log("验证教师ID");
        try {
            validateTeacherId(teacherId, teacherManager);
        } catch (Exception e) {
            logger.log("验证教师ID失败: " + e.getMessage());
            return "添加课程失败: " + e.getMessage();
        }

        logger.log("创建课程对象");
        Course course = new Course(courseId, courseName, teacherId, gradingSystem, credits);

        logger.log("添加课程到管理器");
        courseManager.addCourse(course, user);

        logger.log("课程添加成功: " + course.getCourseId());
        return "课程添加成功";
    } catch (Exception e) {
        logger.log("添加课程时发生错误: " + e.getMessage());
        e.printStackTrace();
        return "添加课程失败: " + e.getMessage();
    }
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
    Course course = (Course) ois.readObject();
    courseManager.updateCourse(course, user);
    logger.log("用户 " + user.getUsername() + " 更新了课程: " + course.getCourseId());
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