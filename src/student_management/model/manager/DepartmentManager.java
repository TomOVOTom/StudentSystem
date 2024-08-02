package student_management.model.manager;

import student_management.model.entity.Department;
import student_management.util.DepartmentExcelUtil;
import student_management.util.LoggerUtil;

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
        LoggerUtil.log("添加院系: " + department.toString());
    }

    public void removeDepartment(String departmentId) {
        departments.remove(departmentId);
        saveDepartmentsToFile();
        LoggerUtil.log("删除院系: " + departmentId);
    }

    public void updateDepartment(Department department) {
        departments.put(department.getDepartmentId(), department);
        saveDepartmentsToFile();
        LoggerUtil.log("更新院系: " + department.getDepartmentId());
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