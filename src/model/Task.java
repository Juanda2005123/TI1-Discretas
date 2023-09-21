package model;

import java.util.*;

public class Task {
    private boolean priority;
    private String title;
    private String description;
    private Calendar deadLine;

    public Task(String title, String description, Calendar deadLine, boolean priority){
        this.title = title;
        this.priority = priority;
        this.deadLine = deadLine;
        this.description = description;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }

    
}
