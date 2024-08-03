package student_management.util.excelutil;

import student_management.model.entity.Department;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepartmentExcelUtil {
    private static final String DEPARTMENT_FILE_NAME = "departments.xlsx";

    public static void saveDepartmentsToFile(HashMap<String, Department> departments) {
        String[] headers = {"院系编号", "院系名称"};
        List<Department> departmentList = new ArrayList<>(departments.values());
        ExcelFileSaver.saveToFile(DEPARTMENT_FILE_NAME, departmentList, headers, (row, department) -> {
            row.createCell(0).setCellValue(department.getDepartmentId());
            row.createCell(1).setCellValue(department.getDepartmentName());
        });
    }

    public static HashMap<String, Department> loadDepartmentsFromFile() {
        return (HashMap<String, Department>) ExcelFileLoader.loadFromFile(DEPARTMENT_FILE_NAME, row -> {
            String departmentId = CellUtil.getCellValueAsString(row.getCell(0));
            String departmentName = CellUtil.getCellValueAsString(row.getCell(1));
            return new Department(departmentId, departmentName);
        }, Department::getDepartmentId);
    }
}