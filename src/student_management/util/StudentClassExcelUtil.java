package student_management.util;

import student_management.model.entity.StudentClass;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentClassExcelUtil {
    private static final String CLASS_FILE_NAME = "student_classes.xlsx";

    public static void saveClassesToFile(HashMap<String, StudentClass> classes) {
        String[] headers = {"班级编号", "班级名称", "院系"};
        List<StudentClass> classList = new ArrayList<>(classes.values());
        ExcelFileSaver.saveToFile(CLASS_FILE_NAME, classList, headers, (row, studentClass) -> {
            row.createCell(0).setCellValue(studentClass.getClassId());
            row.createCell(1).setCellValue(studentClass.getClassName());
            row.createCell(2).setCellValue(studentClass.getDepartment());
        });
    }

    public static HashMap<String, StudentClass> loadClassesFromFile() {
        return (HashMap<String, StudentClass>) ExcelFileLoader.loadFromFile(CLASS_FILE_NAME, row -> {
            String classId = CellUtil.getCellValueAsString(row.getCell(0));
            String className = CellUtil.getCellValueAsString(row.getCell(1));
            String department = CellUtil.getCellValueAsString(row.getCell(2));
            return new StudentClass(classId, className, department);
        }, StudentClass::getClassId);
    }
}