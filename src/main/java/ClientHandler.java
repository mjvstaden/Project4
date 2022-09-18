import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientHandler implements Runnable {
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private static PortHandler portHandler = new PortHandler();
    private static GroupHandler groupHandler = new GroupHandler();
    private static HostnameHandler hostnameHandler = new HostnameHandler();
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Server server;

    private String hostname;

    /**
     * Constructor for the Client Handler
     * @param socket Socket for connection
     */
    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.server = server;

            //getting a hostname
            this.hostname = hostnameHandler.getAvailableHostName();
            server.printLog(this.hostname + " joined the server");
            output.writeObject(hostname);

            this.clients.add(this);
        } catch (IOException i) {
            System.out.println("MARK 1 CLIENTHANDLER");
            server.printLog(this.hostname + " has left the server");
            shutdown();
        } catch (NullPointerException n) {
            server.printLog(this.hostname + " has left the server");
            shutdown();
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
                if (code.startsWith("$[CODE:%25849565]$")) {

                } else if (code.startsWith("$[CODE:%25849566]$")) {

                }
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
     * Function to call the populate function on Ports
     */
    public void populatePorts() {
        portHandler.populatePorts(50000);
    }

    /**
     * Function to call the populate function on Groups
     */
    public void populateGroups() {
        groupHandler.populateGroups(50000);
    }

    /**
     * Function to call the populate function on Hostnames
     */
    public void populateHostnames() {
        hostnameHandler.populateHostNames(50000);
    }

    /**
     * This closes all the inputs, outputs and sockets
     */
    private void shutdown() {
        hostnameHandler.setAvailable(this.hostname);
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
