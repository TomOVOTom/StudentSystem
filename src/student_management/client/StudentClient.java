package student_management.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentClient {
    private String serverAddress;
    private int serverPort;

    public StudentClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public String sendCommand(String command, Object... args) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Sending command: " + command);
            oos.writeObject(command);
            for (Object arg : args) {
                oos.writeObject(arg);
            }
            String response = (String) ois.readObject();
            System.out.println("Received response: " + response);
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "通信错误: " + e.getMessage();
        }
    }
}