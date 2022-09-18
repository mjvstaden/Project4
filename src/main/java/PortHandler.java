import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class for the port handler
 */
public class PortHandler {
    private final List<Port> ports = Collections.synchronizedList(new ArrayList<>());

    /**
     * The constructor for the port handler
     */
    public PortHandler() {
    }

    /**
     * This populates the list full of ports that can be used for communication
     * @param amount The amount of port numbers that needs to be in the pool
     */
    public void populatePorts(int amount) {
        for (int i = 0; i < amount; i++) {
            Port port = new Port(1025 + i);
            ports.add(port);
        }
    }

//    /**
//     * This checks if the port number is used
//     * @param port The port number
//     * @return returns a boolean for if the port number is used
//     */
//    public boolean isUsed(int port) {
//        for (int i = 0; i < ports.size(); i++) {
//            if (ports.get(i).getPort() == port) {
//                return ports.get(i).isUsed();
//            }
//        }
//        return false;
//    }

    /**
     * Get the next available port
     * @return returns the port that is available
     */
    public int getAvailablePort() {
        int port = -1;
        for (int i = 0; i < ports.size(); i++) {
            if (ports.get(i).isUsed() == false) {
                port = ports.get(i).getPort();
                ports.get(i).setUsed(true);
                break;
            }
        }
        return port;
    }

    /**
     * Sets a port that is unavailable, available again
     * @param port The port that must be set available
     */
    public void setAvailable(int port) {
        for (int i = 0; i < ports.size(); i++) {
            if (ports.get(i).getPort() == port) {
                ports.get(i).setUsed(false);
                break;
            }
        }
    }

}
