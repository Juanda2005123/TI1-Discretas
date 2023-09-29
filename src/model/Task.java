package model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Task {
    private boolean priority;
    private String title;
    private String description;
    private String deadLine;
    
    private final String imgSrc;
    
    //private SimpleDateFormat format;

    public String getDead(){
        return deadLine;
    }
    
    public Task(String title, String description, String deadLine, boolean priority){
        this.title = title;
        this.priority = priority;
        this.deadLine = deadLine;
        this.description = description;
        //format = new SimpleDateFormat("dd--mm-yyyy");
        imgSrc = ("/img/edit.png");
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
    
    //public Calendar getDeadLine() {
      //  return deadLine;
    //}

    /**
    public void setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }
    
    public String getSimpleCalendar(){
        return format.format(deadLine);
    }
    */
    public String getImgSrc(){
        return imgSrc;
    }
    
    public String getPriority(){
        return priority ? "Priority" : "Non-priority";
    }
}
