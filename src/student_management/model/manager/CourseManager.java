package student_management.model.manager;

import student_management.model.entity.Course;
import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.CourseExcelUtil;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class CourseManager {
    private ConcurrentHashMap<String, Course> courses;
    private Logger logger;

    public CourseManager(Logger logger) {
        this.courses = new ConcurrentHashMap<>();
        this.logger = logger;
        loadCoursesFromFile();
    }

    private void checkAdminPermission(User user) {
        if (!"admin".equals(user.getRole())) {
            throw new SecurityException("无权限操作");
        }
    }

    private <T> T performAdminOperation(User user, Supplier<T> operation, String logMessage) {
        checkAdminPermission(user);
        T result = operation.get();
        saveCoursesToFile();
        logger.log(logMessage);
        return result;
    }

    public void addCourse(Course course, User user) {
        performAdminOperation(user,
                () -> courses.put(course.getCourseId(), course),
                "用户 " + user.getUsername() + " 添加课程: " + course.toString());
    }

    public void removeCourse(String courseId, User user) {
        performAdminOperation(user,
                () -> courses.remove(courseId),
                "用户 " + user.getUsername() + " 删除课程: " + courseId);
    }

    public void updateCourse(Course course, User user) {
        performAdminOperation(user,
                () -> courses.put(course.getCourseId(), course),
                "用户 " + user.getUsername() + " 更新课程: " + course.getCourseId());
    }

    public String queryCourse(String courseId) {
        Course course = courses.get(courseId);
        return course != null ? course.toString() : "课程未找到";
    }

    private void loadCoursesFromFile() {
        courses.putAll(CourseExcelUtil.loadCoursesFromFile());
    }

    private void saveCoursesToFile() {
        CourseExcelUtil.saveCoursesToFile(new HashMap<>(courses));
    }

    public String queryAllCourses() {
        StringBuilder result = new StringBuilder();
        for (Course course : courses.values()) {
            result.append(course.toString()).append("\n");
        }
        return result.toString();
    }
}