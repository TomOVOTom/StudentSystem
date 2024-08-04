package student_management.util.validator;

import student_management.model.entity.Grade;

public class GradeValidator {
    public static void validateGrade(Grade grade) {
        CommonValidator.validateNotNull(grade, "成绩");
        StudentValidator.validateStudentId(grade.getStudentId());
        CourseValidator.validateCourseId(grade.getCourseId());
        validateScore(grade.getScore());
    }

    public static void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("成绩必须在0到100之间");
        }
    }
}