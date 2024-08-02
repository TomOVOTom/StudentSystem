package student_management.ui.panel;

import student_management.client.StudentClient;
import student_management.ui.StudentSystem;
import student_management.ui.button.course.CourseButtonHandler;
import student_management.ui.button.department.DepartmentButtonHandler;
import student_management.ui.button.student.StudentButtonHandler;
import student_management.ui.button.student_class.StudentClassButtonHandler;
import student_management.ui.button.teacher.TeacherButtonHandler;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel {
    private JPanel panel;

    public ButtonPanel(StudentClient studentClient, InputPanel inputPanel, StudentSystem studentSystem) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 3)); // 修改为6行3列

        StudentButtonHandler studentButtonHandler = new StudentButtonHandler(
            studentClient,
            inputPanel.getIdField(),
            inputPanel.getNameField(),
            inputPanel.getAgeField(),
            studentSystem
        );

        CourseButtonHandler courseButtonHandler = new CourseButtonHandler(
            studentClient,
            inputPanel.getIdField(),
            inputPanel.getCourseIdField(),
            inputPanel.getCourseNameField(),
            inputPanel.getTeacherField(),
            inputPanel.getGradeField(),
            studentSystem
        );

        TeacherButtonHandler teacherButtonHandler = new TeacherButtonHandler(
            studentClient,
            inputPanel.getTeacherIdField(),
            inputPanel.getTeacherNameField(),
            inputPanel.getTeacherSubjectField(),
            studentSystem
        );

        StudentClassButtonHandler classButtonHandler = new StudentClassButtonHandler(
            studentClient,
            inputPanel.getClassIdField(),
            inputPanel.getClassNameField(),
            inputPanel.getDepartmentField(),
            studentSystem
        );

        DepartmentButtonHandler departmentButtonHandler = new DepartmentButtonHandler(
            studentClient,
            inputPanel.getDepartmentIdField(),
            inputPanel.getDepartmentNameField(),
            studentSystem
        );

        // 添加学生相关按钮
        panel.add(studentButtonHandler.createAddStudentButton());
        panel.add(studentButtonHandler.createRemoveStudentButton());
        panel.add(studentButtonHandler.createUpdateStudentButton());
        panel.add(studentButtonHandler.createQueryStudentButton());
        panel.add(studentButtonHandler.createClearStudentFieldsButton());

        // 添加课程相关按钮
        panel.add(courseButtonHandler.createAddCourseButton());
        panel.add(courseButtonHandler.createRemoveCourseButton());
        panel.add(courseButtonHandler.createUpdateCourseButton());
        panel.add(courseButtonHandler.createQueryCourseButton());
        panel.add(courseButtonHandler.createClearCourseFieldsButton());

        // 添加老师相关按钮
        panel.add(teacherButtonHandler.createAddTeacherButton());
        panel.add(teacherButtonHandler.createRemoveTeacherButton());
        panel.add(teacherButtonHandler.createUpdateTeacherButton());
        panel.add(teacherButtonHandler.createQueryTeacherButton());
        panel.add(teacherButtonHandler.createClearTeacherFieldsButton());

        // 添加班级相关按钮
        panel.add(classButtonHandler.createAddStudentClassButton());
        panel.add(classButtonHandler.createRemoveStudentClassButton());
        panel.add(classButtonHandler.createUpdateStudentClassButton());
        panel.add(classButtonHandler.createQueryStudentClassButton());
        panel.add(classButtonHandler.createClearStudentClassFieldsButton());

        // 添加院系相关按钮
        panel.add(departmentButtonHandler.createAddDepartmentButton());
        panel.add(departmentButtonHandler.createRemoveDepartmentButton());
        panel.add(departmentButtonHandler.createUpdateDepartmentButton());
        panel.add(departmentButtonHandler.createQueryDepartmentButton());
        panel.add(departmentButtonHandler.createClearDepartmentFieldsButton());
    }

    public JPanel getPanel() {
        return panel;
    }
}