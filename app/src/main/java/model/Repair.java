package model;

import java.util.Date;

/**
 * Created by 2dam on 23/01/2017.
 */

public class Repair {

    private float timeSpent;
    private Date finishDate;
    private boolean isSolved;
    private String failureLocalization;
    private String replacements;
    private String tools;
    private String repairProcess;
    private String attachments;

    public Repair(float timeSpent, Date finishDate, boolean isSolved, String failureLocalization, String replacements, String tools, String repairProcess, String attachments) {
        this.timeSpent = timeSpent;
        this.finishDate = finishDate;
        this.isSolved = isSolved;
        this.failureLocalization = failureLocalization;
        this.replacements = replacements;
        this.tools = tools;
        this.repairProcess = repairProcess;
        this.attachments = attachments;
    }

    public float getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(float timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public String getFailureLocalization() {
        return failureLocalization;
    }

    public void setFailureLocalization(String failureLocalization) {
        this.failureLocalization = failureLocalization;
    }

    public String getReplacements() {
        return replacements;
    }

    public void setReplacements(String replacements) {
        this.replacements = replacements;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getRepairProcess() {
        return repairProcess;
    }

    public void setRepairProcess(String repairProcess) {
        this.repairProcess = repairProcess;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
}
