private class ClientHostname {

    private String hostname;
    private boolean status;

    public Hostname(String hostname) {
        this.hostname = hostname;
        status = true;
    }

    /**
     * returns the hostname.
     * @return String hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the status of the hostname.
     * @return boolean true if hostname is available.
     */
    public boolean isAvailable() {
        return status;
    }

    /**
     * Sets status to input parameter.
     * @param available true if available.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
