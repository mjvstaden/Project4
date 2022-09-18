import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

public class Server {
    private ServerSocket serverSocket;
    private int port = 1024;
    public static JFrame frame;

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

    private void shutdown() {
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException i) {
            System.out.println("MARK 3 SERVER");
        }
    }

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

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
