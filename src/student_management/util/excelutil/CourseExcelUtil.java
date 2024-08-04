package student_management.util.excelutil;

import student_management.model.entity.Course;
import student_management.util.commonutil.Logger;
import student_management.util.commonutil.LoggerUtil;
import student_management.util.excel.CellUtil;
import student_management.util.excel.ExcelFileLoader;
import student_management.util.excel.ExcelFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseExcelUtil {
    private static final String COURSE_FILE_NAME = "courses.xlsx";
    private static final Logger logger = LoggerUtil.getInstance();
      public static void saveCoursesToFile(HashMap<String, Course> courses) {
        logger.log("开始保存课程到Excel文件");
        String[] headers = {"课程编号", "课程名称", "教师ID", "评分方式", "学分"};
        List<Course> courseList = new ArrayList<>(courses.values());
        ExcelFileSaver.saveToFile(COURSE_FILE_NAME, courseList, headers, (row, course) -> {
            row.createCell(0).setCellValue(course.getCourseId());
            row.createCell(1).setCellValue(course.getCourseName());
            row.createCell(2).setCellValue(course.getTeacherId());
            row.createCell(3).setCellValue(course.getGradingSystem());
            row.createCell(4).setCellValue(course.getCredits());
        });
        logger.log("课程保存到Excel文件完成");
    }

    public static HashMap<String, Course> loadCoursesFromFile() {
        return (HashMap<String, Course>) ExcelFileLoader.loadFromFile(COURSE_FILE_NAME, row -> {
            String courseId = CellUtil.getCellValueAsString(row.getCell(0));
            String courseName = CellUtil.getCellValueAsString(row.getCell(1));
            String teacherId = CellUtil.getCellValueAsString(row.getCell(2));
            String gradingSystem = CellUtil.getCellValueAsString(row.getCell(3));
            float credits = (float) CellUtil.getNumericCellValue(row.getCell(4));
            return new Course(courseId, courseName, teacherId, gradingSystem, credits);
        }, Course::getCourseId);
    }
}