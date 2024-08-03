package student_management.server;

import student_management.model.manager.*;
import student_management.server.handlers.*;
import student_management.util.commonutil.ConsoleLogger;
import student_management.util.commonutil.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
    private StudentManager studentManager;
    private TeacherManager teacherManager;
    private ClassManager classManager;
    private DepartmentManager departmentManager;
    private CommandHandler commandHandler;
    private Logger logger;
    private UserHandler userHandler;
    private CourseHandler courseHandler;

    public StudentServer() {
        this.logger = new ConsoleLogger();
        studentManager = new StudentManager(logger);
        teacherManager = new TeacherManager(logger);
        classManager = new ClassManager(logger);
        departmentManager = new DepartmentManager(logger);
        CourseManager courseManager = new CourseManager(logger);
        GradeManager gradeManager = new GradeManager(logger);
        UserManager userManager = new UserManager(logger);

        StudentHandler studentHandler = new StudentHandler(studentManager, courseHandler, logger);
        TeacherHandler teacherHandler = new TeacherHandler(teacherManager, logger);
        ClassHandler classHandler = new ClassHandler(classManager, logger);
        DepartmentHandler departmentHandler = new DepartmentHandler(departmentManager, logger);
        CourseHandler courseHandler = new CourseHandler(courseManager, logger);
        GradeHandler gradeHandler = new GradeHandler(gradeManager, logger);
        UserHandler userHandler = new UserHandler(userManager, logger);

        commandHandler = new CommandHandler(studentHandler, teacherHandler, classHandler,
                departmentHandler, courseHandler, gradeHandler, userHandler);
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，等待客户端连接...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String command = (String) ois.readObject();
                    System.out.println("Received command: " + command);
                    String response = commandHandler.handleCommand(command, ois);
                    oos.writeObject(response);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentServer server = new StudentServer();
        server.startServer(12345);
    }
}