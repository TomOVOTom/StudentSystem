package student_management.model;

import student_management.util.StudentClassExcelUtil;

import java.util.HashMap;
import java.util.Map;

public class StudentClassManager {
    private Map<String, StudentClass> studentClasses;

    public StudentClassManager() {
        studentClasses = new HashMap<>();
        loadClassesFromFile();
    }

    public void addClass(StudentClass studentClass) {
        studentClasses.put(studentClass.getClassId(), studentClass);
        saveClassesToFile();
    }

    public void removeClass(String classId) {
        studentClasses.remove(classId);
        saveClassesToFile();
    }

    public void updateClass(StudentClass studentClass) {
        studentClasses.put(studentClass.getClassId(), studentClass);
        saveClassesToFile();
    }

    public String queryClass(String classId) {
        StudentClass studentClass = studentClasses.get(classId);
        return studentClass != null ? studentClass.toString() : "班级未找到";
    }

    private void loadClassesFromFile() {
        studentClasses = StudentClassExcelUtil.loadClassesFromFile();
    }

    private void saveClassesToFile() {
        StudentClassExcelUtil.saveClassesToFile((HashMap<String, StudentClass>) studentClasses);
    }
}