package student_management.server;

import student_management.model.StudentClass;
import student_management.model.StudentClassManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class StudentClassCommandHandler {
    private StudentClassManager studentClassManager;

    public StudentClassCommandHandler(StudentClassManager studentClassManager) {
        this.studentClassManager = studentClassManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (command) {
            case "CLASS_ADD_CLASS": {
                StudentClass studentClass = (StudentClass) ois.readObject();
                studentClassManager.addClass(studentClass);
                return "班级添加成功";
            }
            case "CLASS_REMOVE_CLASS": {
                String classId = (String) ois.readObject();
                studentClassManager.removeClass(classId);
                return "班级删除成功";
            }
            case "CLASS_UPDATE_CLASS": {
                StudentClass studentClass = (StudentClass) ois.readObject();
                studentClassManager.updateClass(studentClass);
                return "班级更新成功";
            }
            case "CLASS_QUERY_CLASS": {
                String classId = (String) ois.readObject();
                return studentClassManager.queryClass(classId);
            }
            default:
                return "未知命令";
        }
    }
}