package student_management.server;

import student_management.model.Teacher;
import student_management.model.TeacherManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TeacherCommandHandler {
    private TeacherManager teacherManager;

    public TeacherCommandHandler(TeacherManager teacherManager) {
        this.teacherManager = teacherManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (command) {
            case "TEACHER_ADD_TEACHER":
                Teacher teacher = (Teacher) ois.readObject();
                teacherManager.addTeacher(teacher);
                return "老师添加成功";
            case "TEACHER_REMOVE_TEACHER":
                String id = (String) ois.readObject();
                teacherManager.removeTeacher(id);
                return "老师删除成功";
            case "TEACHER_UPDATE_TEACHER":
                id = (String) ois.readObject();
                String name = (String) ois.readObject();
                String subject = (String) ois.readObject();
                teacherManager.updateTeacher(id, name, subject);
                return "老师更新成功";
            case "TEACHER_QUERY_TEACHER":
                id = (String) ois.readObject();
                return teacherManager.queryTeacher(id);
            case "TEACHER_QUERY_ALL_TEACHERS":
                return queryAllTeachers();
            default:
                return "未知命令";
        }
    }

    private String queryAllTeachers() {
        StringBuilder result = new StringBuilder();
        for (Teacher teacher : teacherManager.getTeachers()) {
            result.append(teacher.toString()).append("\n");
        }
        return result.toString();
    }
}