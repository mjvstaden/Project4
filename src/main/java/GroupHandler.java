import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for group handler
 */
public class GroupHandler {
    private final List<Group> groups = Collections.synchronizedList(new ArrayList<>());

    /**
     * Constructor for the group handler
     */
    public GroupHandler() {
    }

    public void populateGroups(int amount) {
        for (int i = 0; i < amount; i++) {
            Group group = new Group("group" + i);
            groups.add(group);
        }
    }

    /**
     * Get the next available group
     * @return returns the group name that is available
     */
    public String getAvailableGroup() {
        String group = "";

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).isUsed() == false) {
                group = groups.get(i).getName();
                groups.get(i).setUsed(true);
                break;
            }
        }
        return group;
    }

    /**
     * Sets a group that is unavailable, available again
     * @param name The group that must be set available
     */
    public void setAvailible(String name) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName() == name) {
                groups.get(i).setUsed(false);
                break;
            }
        }
    }
}
