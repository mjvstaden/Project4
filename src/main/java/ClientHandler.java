import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
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

    private void shutdown() {

    }
}
