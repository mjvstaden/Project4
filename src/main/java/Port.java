public class Port {
    private int port;
    private boolean used;
    public Port(int port) {
        this.port = port;
        this.used = false;
    }

    public int getPort() {
        return port;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
