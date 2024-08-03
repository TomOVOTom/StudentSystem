package student_management.model.manager;

import student_management.model.entity.Class;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.ClassExcelUtil;

import java.util.HashMap;
import java.util.Map;

public class ClassManager {
    private Map<String, Class> studentClasses;
    private Logger logger;

    public ClassManager(Logger logger) {
        this.logger = logger;
        studentClasses = new HashMap<>();
        loadClassesFromFile();
    }

    public boolean addClass(Class aClass) {
        if (studentClasses.containsKey(aClass.getClassId())) {
            logger.log("添加班级失败: 班级ID已存在 " + aClass.getClassId());
            return false;
        }
        studentClasses.put(aClass.getClassId(), aClass);
        saveClassesToFile();
        logger.log("添加班级成功: " + aClass.toString());
        return true;
    }

    public boolean removeClass(String classId) {
        if (!studentClasses.containsKey(classId)) {
            logger.log("删除班级失败: 班级不存在 " + classId);
            return false;
        }
        studentClasses.remove(classId);
        saveClassesToFile();
        logger.log("删除班级成功: " + classId);
        return true;
    }

    public boolean updateClass(Class aClass) {
        if (!studentClasses.containsKey(aClass.getClassId())) {
            logger.log("更新班级失败: 班级不存在 " + aClass.getClassId());
            return false;
        }
        studentClasses.put(aClass.getClassId(), aClass);
        saveClassesToFile();
        logger.log("更新班级成功: " + aClass.getClassId());
        return true;
    }

    public String queryClass(String classId) {
        Class aClass = studentClasses.get(classId);
        return aClass != null ? aClass.toString() : "班级未找到";
    }

    public String queryAllClasses() {
        StringBuilder result = new StringBuilder();
        for (Class aClass : studentClasses.values()) {
            result.append(aClass.toString()).append("\n");
        }
        return result.toString();
    }

    private void loadClassesFromFile() {
        studentClasses = ClassExcelUtil.loadClassesFromFile();
    }

    private void saveClassesToFile() {
        ClassExcelUtil.saveClassesToFile((HashMap<String, Class>) studentClasses);
    }
}