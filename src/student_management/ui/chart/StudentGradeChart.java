package student_management.ui.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import student_management.model.entity.Student;
import student_management.model.manager.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentGradeChart {
    private StudentManager studentManager;

    public StudentGradeChart(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public JPanel createChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Student> students = studentManager.getStudents();

        for (Student student : students) {
            student.getCourses().forEach((courseId, course) -> {
                dataset.addValue(course.getGrade(), course.getCourseName(), student.getName());
            });
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "学生成绩分布",
                "学生",
                "成绩",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        return new ChartPanel(barChart);
    }

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        StudentGradeChart chart = new StudentGradeChart(studentManager);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chart.createChartPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}