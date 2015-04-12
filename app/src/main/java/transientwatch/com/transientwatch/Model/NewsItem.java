package transientwatch.com.transientwatch.Model;

/**
 * Created by mohamedkomalo on 4/12/15.
 */
public class NewsItem {
    private String Name;
    private String changedAttributeName;
    private String newValue;

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getChangedAttributeName() {
        return changedAttributeName;
    }

    public void setChangedAttributeName(String changedAttributeName) {
        this.changedAttributeName = changedAttributeName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
