package student_management.model.manager;

import student_management.model.entity.Teacher;
import student_management.util.LoggerUtil;
import student_management.util.TeacherExcelUtil;

import java.util.HashMap;
import java.util.LinkedList;

public class TeacherManager {
    private HashMap<String, Teacher> teachers;

    public TeacherManager() {
        teachers = new HashMap<>();
        loadTeachersFromFile();
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
        saveTeachersToFile();
        LoggerUtil.log("添加老师: " + teacher.toString());
    }

    public void removeTeacher(String id) {
        teachers.remove(id);
        saveTeachersToFile();
        LoggerUtil.log("删除老师: " + id);
    }

    public void updateTeacher(String id, String name, String subject) {
        Teacher teacher = teachers.get(id);
        if (teacher != null) {
            teacher.setName(name);
            teacher.setSubject(subject);
            saveTeachersToFile();
            LoggerUtil.log("更新老师: " + id);
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
}