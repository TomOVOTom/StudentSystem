package student_management.model.manager;

import student_management.model.entity.StudentCourse;
import student_management.model.entity.User;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.StudentCourseExcelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentCourseManager {
    private List<StudentCourse> studentCourses;
    private Logger logger;

    public StudentCourseManager(Logger logger) {
        this.studentCourses = new ArrayList<>();
        this.logger = logger;
        loadStudentCoursesFromFile();
    }

    public void selectCourse(StudentCourse studentCourse, User user) {
        studentCourses.add(studentCourse);
        saveStudentCoursesToFile();
        logger.log("用户 " + user.getUsername() + " 为学生 " + studentCourse.getStudentId() + " 选择了课程 " + studentCourse.getCourseId());
    }

    public void dropCourse(String studentId, String courseId, User user) {
        studentCourses.removeIf(sc -> sc.getStudentId().equals(studentId) && sc.getCourseId().equals(courseId));
        saveStudentCoursesToFile();
        logger.log("用户 " + user.getUsername() + " 为学生 " + studentId + " 退选了课程 " + courseId);
    }

    public List<StudentCourse> getStudentCourses(String studentId) {
        return studentCourses.stream()
                .filter(sc -> sc.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    private void loadStudentCoursesFromFile() {
        studentCourses = StudentCourseExcelUtil.loadStudentCoursesFromFile();
    }

    private void saveStudentCoursesToFile() {
        StudentCourseExcelUtil.saveStudentCoursesToFile(studentCourses);
    }
}