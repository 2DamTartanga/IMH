package com.tartanga.dam.imhandroid.model;

/**
 * Created by 2dam on 23/01/2017.
 */

public class Machine {

    private String id;
    private int state;
    private int importance;

    public Machine(String id, int state, int importance) {
        this.id = id;
        this.state = state;
        this.importance = importance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
