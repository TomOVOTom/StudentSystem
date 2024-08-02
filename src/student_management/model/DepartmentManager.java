package student_management.model;

import student_management.util.DepartmentExcelUtil;

import java.util.HashMap;
import java.util.Map;

public class DepartmentManager {
    private Map<String, Department> departments;

    public DepartmentManager() {
        departments = new HashMap<>();
        loadDepartmentsFromFile();
    }

    public void addDepartment(Department department) {
        departments.put(department.getDepartmentId(), department);
        saveDepartmentsToFile();
    }

    public void removeDepartment(String departmentId) {
        departments.remove(departmentId);
        saveDepartmentsToFile();
    }

    public void updateDepartment(Department department) {
        departments.put(department.getDepartmentId(), department);
        saveDepartmentsToFile();
    }

    public String queryDepartment(String departmentId) {
        Department department = departments.get(departmentId);
        return department != null ? department.toString() : "院系未找到";
    }

    private void loadDepartmentsFromFile() {
        departments = DepartmentExcelUtil.loadDepartmentsFromFile();
    }

    private void saveDepartmentsToFile() {
        DepartmentExcelUtil.saveDepartmentsToFile((HashMap<String, Department>) departments);
    }
}