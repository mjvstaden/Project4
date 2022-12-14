/**
 * This is the port class
 */
public class Port {
    private int port;
    private boolean status;

    /**
     * Constructor for the port
     * @param port
     */
    public Port(int port) {
        this.port = port;
        this.status = false;
    }

    /**
     * Returns the port number
     * @return the port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns if the port is used or not
     * @return boolean for used or not
     */
    public boolean isUsed() {
        return status;
    }

    /**
     * Sets the status to true or false
     * @param status the state
     */
    public void setUsed(boolean status) {
        this.status = status;
    }
}
