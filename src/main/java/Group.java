/**
 * This is the group class
 */
public class Group {
    private String name;
    private boolean status;

    /**
     * Constructor for a group
     * @param name
     */
    public Group(String name) {
        this.name = name;
        this.status = false;
    }

    /**
     * Getting the name of the group
     * @return the name of the group
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns if the group is used or not
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
