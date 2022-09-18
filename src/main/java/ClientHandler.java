import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static PortHandler portHandler = new PortHandler();
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     * Constructor for the Client Handler
     * @param socket Socket for connection
     */
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());

            this.clients.add(this);
        } catch (IOException i) {
            System.out.println("MARK 1 CLIENTHANDLER");
        }
    }

    /**
     * Function that runs after the object is made
     */
    @Override
    public void run() {
        while (true) {
            try {
                String code = (String) input.readObject();

            } catch (IOException i) {
                System.out.println("MARK 2 CLIENTHANDLER");
            } catch (ClassNotFoundException e) {
                System.out.println("MARK 3 CLIENTHANDLER");
            }
        }
    }

    /**
     * Function to get the size of the clients list
     * @return returns the amount of clients
     */
    public int getClientSize() {
        return clients.size();
    }

    /**
     * Function to call the populate function on porthandler
     */
    public void populatePorts() {
        portHandler.populatePorts(50000);
    }

    /**
     * This closes all the inputs, outputs and sockets
     */
    private void shutdown() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (socket.isConnected()) {
                socket.close();
            }
        } catch (IOException i) {
            System.out.println("MARK 4 CLIENTHANDLER");
        }
    }
}
