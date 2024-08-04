package student_management.util.validator;

import student_management.model.entity.Course;
import student_management.model.manager.TeacherManager;

public class CourseValidator {
    public static void validateCourse(Course course) {
        CommonValidator.validateNotNull(course, "课程");
        CommonValidator.validateStringLength(course.getCourseId(), 5, 20, "课程ID");
        CommonValidator.validateStringLength(course.getCourseName(), 2, 100, "课程名称");
        CommonValidator.validateStringLength(course.getTeacherId(), 2, 50, "教师姓名");
        // 添加更多特定于课程的验证
    }

    public static void validateCourseId(String courseId) {
        CommonValidator.validateStringLength(courseId, 5, 20, "课程ID");
    }

    public static void validateTeacherId(String teacherId, TeacherManager teacherManager) {
        CommonValidator.validateStringLength(teacherId, 5, 20, "教师ID");
        if (teacherManager.getTeacher(teacherId) == null) {
            throw new IllegalArgumentException("教师ID不存在: " + teacherId);
        }
    }
}