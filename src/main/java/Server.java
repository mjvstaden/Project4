import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class for the server
 */
public class Server {
    private ServerSocket serverSocket;
    private int port = 1024;

    /**
     * Constructor for the server
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException i) {
            System.out.println("MARK 1 SERVER");
        }
    }

    /**
     * Function that starts the server and loops the whole time
     * and accepting new clients
     */
    private void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                if (client.getClientSize() <= 1) {
                    client.populatePorts();
                }
                Thread thread = new Thread(client);
                thread.start();
            } catch (IOException i) {
                System.out.println("MARK 2 SERVER");
            }
        }
    }

    /**
     * Shuts down the server
     */
    private void shutdown() {
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException i) {
            System.out.println("MARK 3 SERVER");
        }
    }

    /**
     * Main function for the server
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
