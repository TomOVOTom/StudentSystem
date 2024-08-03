package student_management.model.manager;

import student_management.model.entity.Department;
import student_management.util.commonutil.Logger;
import student_management.util.excelutil.DepartmentExcelUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentManager {
    private Map<String, Department> departments;
    private Logger logger;

    public DepartmentManager(Logger logger) {
        this.logger = logger;
        loadDepartmentsFromFile();
    }

    public void addDepartment(Department department) {
        departments.put(department.getDepartmentId(), department);
        saveDepartmentsToFile();
        logger.log("添加院系: " + department.toString());
    }

    public void removeDepartment(String departmentId) {
        if (departments.remove(departmentId) != null) {
            saveDepartmentsToFile();
            logger.log("删除院系: " + departmentId);
        } else {
            logger.log("尝试删除不存在的院系: " + departmentId);
        }
    }

    public void updateDepartment(Department department) {
        if (departments.containsKey(department.getDepartmentId())) {
            departments.put(department.getDepartmentId(), department);
            saveDepartmentsToFile();
            logger.log("更新院系: " + department.getDepartmentId());
        } else {
            logger.log("尝试更新不存在的院系: " + department.getDepartmentId());
        }
    }

    public String queryDepartment(String departmentId) {
        Department department = departments.get(departmentId);
        if (department != null) {
            logger.log("查询院系: " + departmentId);
            return department.toString();
        } else {
            logger.log("查询不存在的院系: " + departmentId);
            return "院系未找到";
        }
    }

    public String queryAllDepartments() {
        logger.log("查询所有院系");
        return departments.values().stream()
                .map(Department::toString)
                .collect(Collectors.joining("\n"));
    }

    private void loadDepartmentsFromFile() {
        try {
            departments = DepartmentExcelUtil.loadDepartmentsFromFile();
            logger.log("从文件加载院系数据");
        } catch (Exception e) {
            logger.log("加载院系数据失败: " + e.getMessage());
            departments = new HashMap<>();
        }
    }

    private void saveDepartmentsToFile() {
        try {
            DepartmentExcelUtil.saveDepartmentsToFile(new HashMap<>(departments));
            logger.log("保存院系数据到文件");
        } catch (Exception e) {
            logger.log("保存院系数据失败: " + e.getMessage());
        }
    }
}