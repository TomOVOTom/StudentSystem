package student_management;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class StudentServer {
    private StudentManager studentManager;

    public StudentServer() {
        studentManager = new StudentManager();
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，等待客户端连接...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String command = (String) ois.readObject();
                    String response = handleCommand(command, ois);
                    oos.writeObject(response);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handleCommand(String command, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (command) {
            case "ADD_STUDENT":
                Student student = (Student) ois.readObject();
                studentManager.addStudent(student);
                return "学生添加成功";
            case "REMOVE_STUDENT":
                String id = (String) ois.readObject();
                studentManager.removeStudent(id);
                return "学生删除成功";
            case "UPDATE_STUDENT":
                id = (String) ois.readObject();
                String name = (String) ois.readObject();
                int age = (int) ois.readObject();
                studentManager.updateStudent(id, name, age);
                return "学生更新成功";
            case "QUERY_STUDENT":
                id = (String) ois.readObject();
                return studentManager.queryStudent(id);
            case "QUERY_COURSE":
                id = (String) ois.readObject();
                String course = (String) ois.readObject();
                return studentManager.queryCourse(id, course);
            case "QUERY_ALL_STUDENTS":
                return queryAllStudents();
            default:
                return "未知命令";
        }
    }

    private String queryAllStudents() {
        StringBuilder result = new StringBuilder();
        LinkedList<Student> students = studentManager.getStudents();
        for (Student student : students) {
            result.append(student.toString()).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        StudentServer server = new StudentServer();
        server.startServer(12345);
    }
}