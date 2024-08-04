package student_management.util.validator;

import student_management.model.entity.Student;

public class StudentValidator {
    public static void validateStudent(Student student) {
        CommonValidator.validateNotNull(student, "学生");
        CommonValidator.validateStringLength(student.getId(), 5, 20, "学生ID");
        CommonValidator.validateStringLength(student.getName(), 2, 50, "学生姓名");
        CommonValidator.validatePositiveInteger(student.getAge(), "年龄");
        // 添加更多特定于学生的验证
    }

    public static void validateStudentId(String studentId) {
        CommonValidator.validateStringLength(studentId, 5, 20, "学生ID");
    }
}