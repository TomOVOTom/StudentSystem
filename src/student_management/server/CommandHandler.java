package student_management.server;

import student_management.server.handlers.*;
import student_management.util.systeminfo.SystemInfoUtil;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandHandler {
    private StudentHandler studentHandler;
    private TeacherHandler teacherHandler;
    private ClassHandler classHandler;
    private DepartmentHandler departmentHandler;
    private CourseHandler courseHandler;
    private GradeHandler gradeHandler;
    private UserHandler userHandler;
    private StudentCourseHandler studentCourseHandler;

    public CommandHandler(StudentHandler studentHandler,
                          TeacherHandler teacherHandler,
                          ClassHandler classHandler,
                          DepartmentHandler departmentHandler,
                          CourseHandler courseHandler,
                          GradeHandler gradeHandler,
                          UserHandler userHandler,
                          StudentCourseHandler studentCourseHandler) {
        this.studentHandler = studentHandler;
        this.teacherHandler = teacherHandler;
        this.classHandler = classHandler;
        this.departmentHandler = departmentHandler;
        this.courseHandler = courseHandler;
        this.gradeHandler = gradeHandler;
        this.userHandler = userHandler;
        this.studentCourseHandler = studentCourseHandler;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        if (command.startsWith("STUDENT_COURSE_")) {
            return studentCourseHandler.handleCommand(command, ois);
        } else if (command.startsWith("STUDENT_")) {
            return studentHandler.handleCommand(command, ois);
        } else if (command.startsWith("TEACHER_")) {
            return teacherHandler.handleCommand(command, ois);
        } else if (command.startsWith("CLASS_")) {
            return classHandler.handleCommand(command, ois);
        } else if (command.startsWith("DEPARTMENT_")) {
            return departmentHandler.handleCommand(command, ois);
        } else if (command.startsWith("COURSE_")) {
            return courseHandler.handleCommand(command, ois);
        } else if (command.startsWith("GRADE_")) {
            return gradeHandler.handleCommand(command, ois);
        } else if (command.startsWith("USER_")) {
            return userHandler.handleCommand(command, ois);
        } else if (command.startsWith("PING")) {
            return SystemInfoUtil.getSystemInfo();
        } else {
            return "未知命令";
        }
    }
}