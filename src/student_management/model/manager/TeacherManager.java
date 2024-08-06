package student_management.model.manager;

import student_management.model.entity.Teacher;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.TeacherExcelUtil;

import java.util.HashMap;
import java.util.LinkedList;

public class TeacherManager {
    private HashMap<String, Teacher> teachers;
    private Logger logger;

   public TeacherManager(Logger logger) {
    this.logger = logger;
    logger.log("初始化 TeacherManager");
    teachers = new HashMap<>();
    loadTeachersFromFile();
    logger.log("TeacherManager 初始化完成,加载了 " + teachers.size() + " 条教师记录");
}

    public void printAllTeachers() {
        System.out.println("打印所有教师信息:");
        for (Teacher teacher : teachers.values()) {
            System.out.println(teacher);
        }
    }

   public void addTeacher(Teacher teacher) {
    try {
        teachers.put(teacher.getId(), teacher);
        saveTeachersToFile();
        logger.log("添加老师: " + teacher.toString());
    } catch (Exception e) {
        logger.log("添加老师失败: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void removeTeacher(String id) {
        teachers.remove(id);
        saveTeachersToFile();
        logger.log("删除老师: " + id);
    }

    public void updateTeacher(Teacher teacher) {
        if (teachers.containsKey(teacher.getId())) {
            teachers.put(teacher.getId(), teacher);
            saveTeachersToFile();
            logger.log("更新老师: " + teacher.getId());
        } else {
            logger.log("更新老师失败: 未找到ID为 " + teacher.getId() + " 的老师");
        }
    }

    public Teacher getTeacher(String id) {
        return teachers.get(id);
    }

    public LinkedList<Teacher> getTeachers() {
        return new LinkedList<>(teachers.values());
    }

    public void saveTeachersToFile() {
        TeacherExcelUtil.saveTeachersToFile(teachers);
    }

    public void loadTeachersFromFile() {
        teachers = TeacherExcelUtil.loadTeachersFromFile();
    }

    public String queryTeacher(String id) {
        Teacher teacher = getTeacher(id);
        return teacher != null ? teacher.toString() : "老师未找到";
    }

    public String queryAllTeachers() {
        StringBuilder result = new StringBuilder();
        for (Teacher teacher : teachers.values()) {
            result.append(teacher.toString()).append("\n");
        }
        return result.toString();
    }
}