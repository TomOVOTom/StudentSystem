package student_management.model.manager;

import student_management.model.entity.Grade;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.GradeExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class GradeManager {
    private List<Grade> grades;
    private Logger logger;

    public GradeManager(Logger logger) {
        grades = new ArrayList<>();
        loadGradesFromFile();
        this.logger = logger;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
        saveGradesToFile();
        logger.log("添加成绩: " + grade.toString());
    }

    public void removeGrade(String studentId, String courseId) {
        grades.removeIf(grade -> grade.getStudentId().equals(studentId) && grade.getCourseId().equals(courseId));
        saveGradesToFile();
        logger.log("删除成绩: 学生ID=" + studentId + ", 课程ID=" + courseId);
    }

    public void updateGrade(Grade updatedGrade) {
        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            if (grade.getStudentId().equals(updatedGrade.getStudentId()) && grade.getCourseId().equals(updatedGrade.getCourseId())) {
                grades.set(i, updatedGrade);
                saveGradesToFile();
                logger.log("更新成绩: " + updatedGrade.toString());
                return;
            }
        }
    }

    public Grade getGrade(String studentId, String courseId) {
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId) && grade.getCourseId().equals(courseId)) {
                return grade;
            }
        }
        return null;
    }

    public List<Grade> getAllGrades() {
        return new ArrayList<>(grades);
    }

    private void loadGradesFromFile() {
        grades = GradeExcelUtil.loadGradesFromFile();
    }

    private void saveGradesToFile() {
        GradeExcelUtil.saveGradesToFile(grades);
    }
}