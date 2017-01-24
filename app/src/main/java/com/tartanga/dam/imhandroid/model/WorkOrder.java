package com.tartanga.dam.imhandroid.model;

/**
 * Created by 2dam on 23/01/2017.
 */

public class WorkOrder {

    private String id;
    private String typeOfManteinance;
    private String annotations;

    public WorkOrder(String id, String typeOfManteinance, String annotations) {
        this.id = id;
        this.typeOfManteinance = typeOfManteinance;
        this.annotations = annotations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeOfManteinance() {
        return typeOfManteinance;
    }

    public void setTypeOfManteinance(String typeOfManteinance) {
        this.typeOfManteinance = typeOfManteinance;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }
}
