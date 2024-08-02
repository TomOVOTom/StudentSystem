package student_management.server;

import student_management.model.manager.DepartmentManager;
import student_management.model.manager.StudentClassManager;
import student_management.model.manager.StudentManager;
import student_management.model.manager.TeacherManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
    private StudentManager studentManager;
    private TeacherManager teacherManager;
    private StudentClassManager studentClassManager;
    private DepartmentManager departmentManager;
    private CommandHandler commandHandler;

    public StudentServer() {
        studentManager = new StudentManager();
        teacherManager = new TeacherManager();
        studentClassManager = new StudentClassManager();
        departmentManager = new DepartmentManager();
        StudentCommandHandler studentCommandHandler = new StudentCommandHandler(studentManager);
        TeacherCommandHandler teacherCommandHandler = new TeacherCommandHandler(teacherManager);
        StudentClassCommandHandler studentClassCommandHandler = new StudentClassCommandHandler(studentClassManager);
        DepartmentCommandHandler departmentCommandHandler = new DepartmentCommandHandler(departmentManager);
        commandHandler = new CommandHandler(studentCommandHandler, teacherCommandHandler, studentClassCommandHandler, departmentCommandHandler);
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