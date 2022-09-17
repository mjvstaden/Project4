import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private int port = 1024;

    public Server() {
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException i) {
            System.out.println("MARK 1 SERVER");
        }
    }

    private void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

            } catch (IOException i) {
                System.out.println("MARK 2 SERVER");
            }
        }
    }

    private void shutdown() {

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
