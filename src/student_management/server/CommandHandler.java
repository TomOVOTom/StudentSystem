package student_management.server;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandHandler {
    private StudentCommandHandler studentCommandHandler;
    private TeacherCommandHandler teacherCommandHandler;
    private StudentClassCommandHandler studentClassCommandHandler;
    private DepartmentCommandHandler departmentCommandHandler;

    public CommandHandler(StudentCommandHandler studentCommandHandler, TeacherCommandHandler teacherCommandHandler, StudentClassCommandHandler studentClassCommandHandler, DepartmentCommandHandler departmentCommandHandler) {
        this.studentCommandHandler = studentCommandHandler;
        this.teacherCommandHandler = teacherCommandHandler;
        this.studentClassCommandHandler = studentClassCommandHandler;
        this.departmentCommandHandler = departmentCommandHandler;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        if (command.startsWith("STUDENT_")) {
            return studentCommandHandler.handleCommand(command, ois);
        } else if (command.startsWith("TEACHER_")) {
            return teacherCommandHandler.handleCommand(command, ois);
        } else if (command.startsWith("CLASS_")) {
            return studentClassCommandHandler.handleCommand(command, ois);
        } else if (command.startsWith("DEPARTMENT_")) {
            return departmentCommandHandler.handleCommand(command, ois);
        } else {
            return "未知命令";
        }
    }
}