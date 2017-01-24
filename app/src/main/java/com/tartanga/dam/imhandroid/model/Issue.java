package com.tartanga.dam.imhandroid.model;

import java.util.Date;

/**
 * Created by 2dam on 23/01/2017.
 */

public class Issue {

    private String failureType;
    private String severity;
    private String issue;
    private String description;
    private Date date;

    public Issue(String failureType, String severity, String issue, String description, Date date) {
        this.failureType = failureType;
        this.severity = severity;
        this.issue = issue;
        this.description = description;
        this.date = date;
    }

    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
