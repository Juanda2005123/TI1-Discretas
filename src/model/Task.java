package model;

import java.time.LocalDate;

public class Task implements Comparable{
    private PriorityLevel priority;
    private int priorityNum;
    private String title;
    private String description;    
    
    private LocalDate deadLine;
    
    private final String imgSrc;
    private int id;

    
    
    public Task(String title, String description, LocalDate deadLine, PriorityLevel priority){
        this.title = title;
        this.priority = priority;
        this.deadLine = deadLine;
        this.description = description;
        imgSrc = ("/img/edit.png");
        setPriorityNum();
        
    }
    
    public Task(Task newTask){
        this.title = newTask.getTitle();
        this.priority = newTask.getPriority();
        this.deadLine = newTask.getDeadLine();
        this.description = newTask.getDescription();
        this.id = newTask.getId();
        imgSrc = ("/img/edit.png");
        setPriorityNum();
    }
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
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
    
    private void setPriorityNum(){
        priorityNum = 0;

        if(priority==PriorityLevel.LOW){
            priorityNum = 1;
        } else if(priority==PriorityLevel.MEDIUM){
            priorityNum = 2;
        } else if(priority==PriorityLevel.HIGH){
            priorityNum = 3;
        } else if(priority==PriorityLevel.IMMEDIATE){
            priorityNum = 4;
        }
    }
    
    public int getPriorityNum(){
        return priorityNum;
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
        setPriorityNum();
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Object o) { //1. This>o 0. This==o  -1 This<o
        int num = 0;
        Task taskCompare = (Task)o;
        if(priorityNum > taskCompare.getPriorityNum()){
            num = 1;
        } else if(priorityNum < taskCompare.getPriorityNum()){
            num = -1;
        }
        return num;
        
    }
    
    public int compareToDeadLine(Object o){
        
        int num = 0;
        Task taskCompare = (Task)o;
        
        if(deadLine.isAfter(taskCompare.getDeadLine())){
            num = 1;
        } else if(deadLine.isBefore(taskCompare.getDeadLine())){
            num = -1;
        }
        return num;
    }
}
