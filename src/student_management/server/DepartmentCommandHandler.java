package student_management.server;

import student_management.model.Department;
import student_management.model.DepartmentManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class DepartmentCommandHandler {
    private DepartmentManager departmentManager;

    public DepartmentCommandHandler(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (command) {
            case "DEPARTMENT_ADD_DEPARTMENT": {
                Department department = (Department) ois.readObject();
                departmentManager.addDepartment(department);
                return "院系添加成功";
            }
            case "DEPARTMENT_REMOVE_DEPARTMENT": {
                String departmentId = (String) ois.readObject();
                departmentManager.removeDepartment(departmentId);
                return "院系删除成功";
            }
            case "DEPARTMENT_UPDATE_DEPARTMENT": {
                Department department = (Department) ois.readObject();
                departmentManager.updateDepartment(department);
                return "院系更新成功";
            }
            case "DEPARTMENT_QUERY_DEPARTMENT": {
                String departmentId = (String) ois.readObject();
                return departmentManager.queryDepartment(departmentId);
            }
            default:
                return "未知命令";
        }
    }
}