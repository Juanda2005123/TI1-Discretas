package model;

import java.time.LocalDate;

public class Task {
    private PriorityLevel priority;
    private String title;
    private String description;    
    
    private LocalDate deadLine;
    
    private final String imgSrc;

    
    
    public Task(String title, String description, LocalDate deadLine, PriorityLevel priority){
        this.title = title;
        this.priority = priority;
        this.deadLine = deadLine;
        this.description = description;
        //format = new SimpleDateFormat("dd--mm-yyyy");
        imgSrc = ("/img/edit.png");
    }

    public String priorityToString(){

        String msg = "NON";

        if(priority==PriorityLevel.LOW){
            msg = "LOW";
        } else if(priority==PriorityLevel.MEDIUM){
            msg = "MEDIUM";
        } else if(priority==PriorityLevel.HIGH){
            msg = "HIGH";
        } else if(priority==PriorityLevel.IMMEDIATE){
            msg = "IMMEDIATE";
        }
        
        return msg;
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
    
    public void setDeadLine(LocalDate d){
        this.deadLine = d;
    }
    
    public LocalDate getDeadLine(){
        return deadLine;
    }
    
    public String getDeadLineToString(){
        return deadLine.toString();
    }
    
    public String getImgSrc(){
        return imgSrc;
    }
    
    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public PriorityLevel getPriority() {
        return priority;
    }
}
