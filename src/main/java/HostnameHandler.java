import java.util.ArrayList;
import java.util.List;

private class HostnameHandler {
    private final List<Hostname> hostnames = Collections.synchronizedList(new ArrayList<>());

    /**
     * Populates the hostnames list with hostnames
     * @param amount the number of hostnames to make.
     */
    public void populateHostNames(int amount) {
        for (int i = 0; i < amount; i++) {
            Hostname hostname = new Hostname("Client." + i);
            hostnames.add(hostname);
        }
    }

    /**
     * This checks if the hostname is used
     * @param hostname The hostname
     * @return returns a boolean for if the hostname is used
     */
    public boolean isUsed(int hostname) {
        for (int i = 0; i < hostnames.size(); i++) {
            if (hostnames.get(i).getHostname() == hostname) {
                return hostnames.get(i).isAvailable();
            }
        }
        return false;
    }
    /**
     * Sets a hostname that is unavailable, available again
     * @param hostname The hostname that must be set available
     */
    public void setAvailable(int hostname) {
        for (int i = 0; i < hostnames.size(); i++) {
            if (hostnames.get(i).getHostname() == hostname) {
                hostnames.get(i).setStatus(false);
                break;
            }
        }
    }
}