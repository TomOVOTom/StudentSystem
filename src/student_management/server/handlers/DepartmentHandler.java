package student_management.server.handlers;

import student_management.model.entity.Department;
import student_management.model.entity.User;
import student_management.model.manager.DepartmentManager;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class DepartmentHandler {
    private DepartmentManager departmentManager;
    private Logger logger;

    public DepartmentHandler(DepartmentManager departmentManager, Logger logger) {
        this.departmentManager = departmentManager;
        this.logger = logger;
    }

    public String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        User user = (User) ois.readObject();
        try {
            switch (command) {
                case "DEPARTMENT_ADD_DEPARTMENT":
                    return addDepartment(ois, user);
                case "DEPARTMENT_REMOVE_DEPARTMENT":
                    return removeDepartment(ois, user);
                case "DEPARTMENT_UPDATE_DEPARTMENT":
                    return updateDepartment(ois, user);
                case "DEPARTMENT_QUERY_DEPARTMENT":
                    return queryDepartment(ois, user);
                case "DEPARTMENT_QUERY_ALL_DEPARTMENTS":
                    return queryAllDepartments(user);
                default:
                    return "未知命令";
            }
        } catch (Exception e) {
            logger.log("错误: " + e.getMessage());
            return "操作失败: " + e.getMessage();
        }
    }

    private String addDepartment(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Department department = (Department) ois.readObject();
        departmentManager.addDepartment(department);
        logger.log("用户 " + user.getUsername() + " 添加了院系: " + department.toString());
        return "院系添加成功";
    }

    private String removeDepartment(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        String departmentId = (String) ois.readObject();
        departmentManager.removeDepartment(departmentId);
        logger.log("用户 " + user.getUsername() + " 删除了院系: " + departmentId);
        return "院系删除成功";
    }

    private String updateDepartment(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        if (!user.getRole().equals("admin")) {
            throw new SecurityException("无权限操作");
        }
        Department department = (Department) ois.readObject();
        departmentManager.updateDepartment(department);
        logger.log("用户 " + user.getUsername() + " 更新了院系: " + department.toString());
        return "院系更新成功";
    }

    private String queryDepartment(ObjectInputStream ois, User user) throws IOException, ClassNotFoundException {
        String departmentId = (String) ois.readObject();
        String result = departmentManager.queryDepartment(departmentId);
        logger.log("用户 " + user.getUsername() + " 查询了院系: " + departmentId);
        return result;
    }

    private String queryAllDepartments(User user) {
        String result = departmentManager.queryAllDepartments();
        logger.log("用户 " + user.getUsername() + " 查询了所有院系");
        return result;
    }
}