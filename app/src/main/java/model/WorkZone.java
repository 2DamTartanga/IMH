package model;

/**
 * Created by 2dam on 23/01/2017.
 */

public class WorkZone {

    private String id;
    private String name;

    public WorkZone(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
