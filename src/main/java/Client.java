import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class for the Client
 */
public class Client {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int serverport = 1024;
    private String ServerIp;
    /**
     * Constructor for the Client
     */
    public Client() {

        //socket = new Socket(1024);
    }

    /**
     * Main function that loops and sends information
     */
    private void start() {

    }

    /**
     * Main function for client
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
