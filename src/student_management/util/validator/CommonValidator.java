package student_management.util.validator;

public class CommonValidator {
    public static void validateStringLength(String value, int minLength, int maxLength, String fieldName) {
        if (value == null || value.length() < minLength || value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " 长度必须在 " + minLength + " 到 " + maxLength + " 之间");
        }
    }

    public static void validatePositiveInteger(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " 必须是正整数");
        }
    }

    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " 不能为空");
        }
    }
}