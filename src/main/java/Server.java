import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

/**
 * Class for the server
 */
public class Server {
    private ServerSocket serverSocket;
    private int port = 1024;
    public static JFrame frame;

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

<<<<<<< HEAD
    /**
     * Main function for the server
     * @param args
     */
=======
    private static void intitializeGUI() {

        frame = new JFrame();
        frame.setBounds(100, 100, 380, 600);
        frame.getContentPane().setLayout(null);
        JLabel lblServer = new JLabel("Server");
        lblServer.setFont(new Font("Calibri", Font.BOLD, 28));
        lblServer.setBounds(130, 1, 191, 51);
        frame.getContentPane().add(lblServer);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(12, 64, 360, 550);
        frame.getContentPane().add(textArea);

        frame.setVisible(true);
    }

>>>>>>> da1f0c09ccbac4d025668c8c2ec3c7079bb2afd5
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
