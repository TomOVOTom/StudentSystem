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
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try (Socket socket = new Socket(serverAddress, serverPort);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                System.out.println("发送命令: " + command);
                oos.writeObject(command);
                for (Object arg : args) {
                    oos.writeObject(arg);
                }
                String response = (String) ois.readObject();
                System.out.println("收到响应: " + response);
                return response;
            } catch (IOException e) {
                System.err.println("通信错误 (尝试 " + (retryCount + 1) + "/" + maxRetries + "): " + e.getMessage());
                retryCount++;
                if (retryCount == maxRetries) {
                    return "通信错误: 无法连接到服务器，请确保服务器正在运行。";
                }
                try {
                    Thread.sleep(1000); // 等待1秒后重试
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } catch (ClassNotFoundException e) {
                System.err.println("类型错误: " + e.getMessage());
                return "类型错误: " + e.getMessage();
            }
        }
        return "通信错误: 无法连接到服务器，请确保服务器正在运行。";
    }
}