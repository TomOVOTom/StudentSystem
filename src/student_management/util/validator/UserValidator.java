package student_management.util.validator;

public class UserValidator {
    public static void validateUsername(String username) {
        CommonValidator.validateStringLength(username, 3, 20, "用户名");
    }

    public static void validatePassword(String password) {
        CommonValidator.validateStringLength(password, 6, 20, "密码");
    }

    public static void validateRole(String role) {
        if (!role.equals("admin") && !role.equals("teacher") && !role.equals("student")) {
            throw new IllegalArgumentException("角色必须是 admin、teacher 或 student");
        }
    }
}