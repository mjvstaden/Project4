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

    public int getClientSize() {
        return clients.size();
    }

    public void populatePorts() {
        portHandler.populatePorts(50000);
    }

    private void shutdown() {

    }
}
