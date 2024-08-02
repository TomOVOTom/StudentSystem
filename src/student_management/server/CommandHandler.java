package student_management.server;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandHandler {
    private StudentCommandHandler studentCommandHandler;
    private TeacherCommandHandler teacherCommandHandler;

    public CommandHandler(StudentCommandHandler studentCommandHandler, TeacherCommandHandler teacherCommandHandler) {
        this.studentCommandHandler = studentCommandHandler;
        this.teacherCommandHandler = teacherCommandHandler;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        if (command.startsWith("STUDENT_")) {
            return studentCommandHandler.handleCommand(command, ois);
        } else if (command.startsWith("TEACHER_")) {
            return teacherCommandHandler.handleCommand(command, ois);
        } else {
            return "未知命令";
        }
    }
}