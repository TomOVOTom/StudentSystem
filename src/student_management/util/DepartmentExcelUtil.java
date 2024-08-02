package student_management.util;

import student_management.model.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepartmentExcelUtil {
    private static final String DEPARTMENT_FILE_NAME = "departments.xlsx";

    public static void saveDepartmentsToFile(HashMap<String, Department> departments) {
        String[] headers = {"院系编号", "院系名称"};
        List<Department> departmentList = new ArrayList<>(departments.values());
        ExcelUtil.saveToFile(DEPARTMENT_FILE_NAME, departmentList, headers, (row, department) -> {
            row.createCell(0).setCellValue(department.getDepartmentId());
            row.createCell(1).setCellValue(department.getDepartmentName());
        });
    }

    public static HashMap<String, Department> loadDepartmentsFromFile() {
        return (HashMap<String, Department>) ExcelUtil.loadFromFile(DEPARTMENT_FILE_NAME, row -> {
            String departmentId = ExcelUtil.getCellValueAsString(row.getCell(0));
            String departmentName = ExcelUtil.getCellValueAsString(row.getCell(1));
            return new Department(departmentId, departmentName);
        }, Department::getDepartmentId);
    }
}