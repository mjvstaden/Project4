import java.util.ArrayList;
import java.util.List;

public class PortHandler {
    private final List<Port> ports = new ArrayList<>();
    public PortHandler() {
    }

    public void populatePorts(int amount) {
        for (int i = 0; i < amount; i++) {
            Port port = new Port(1025 + i);
            ports.add(port);
        }
    }

    public boolean isUsed(int port) {
        for (int i = 0; i < ports.size(); i++) {
            if (ports.get(i).getPort() == port) {
                return ports.get(i).isUsed();
            }
        }
        return false;
    }

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

    public void setAvailable(int port) {
        for (int i = 0; i < ports.size(); i++) {
            if (ports.get(i).getPort() == port) {
                ports.get(i).setUsed(false);
                break;
            }
        }
    }

}
