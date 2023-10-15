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

    
    
    
    
    // The `public Task(String title, String description, LocalDate deadLine, PriorityLevel priority)`
    // constructor is creating a new `Task` object with the given parameters. It initializes the
    // `title`, `description`, `deadLine`, and `priority` fields of the `Task` object with the
    // corresponding values passed as arguments. It also sets the `imgSrc` field to "/img/edit.png" and
    // calls the `setPriorityNum()` method to set the `priorityNum` field based on the `priority`
    // value.
    public Task(String title, String description, LocalDate deadLine, PriorityLevel priority){
        this.title = title;
        this.priority = priority;
        this.deadLine = deadLine;
        this.description = description;
        imgSrc = ("/img/edit.png");
        setPriorityNum();
        
    }
    
    // The `public Task(Task newTask)` constructor is creating a new `Task` object by copying the
    // values from another `Task` object (`newTask`). It sets the `title`, `priority`, `deadLine`,
    // `description`, and `id` fields of the new `Task` object to the corresponding values of the `newTask`
    // object. It also sets the `imgSrc` field to "/img/edit.png" and calls the `setPriorityNum()`
    // method to set the `priorityNum` field based on the `priority` value.
    public Task(Task newTask){
        this.title = newTask.getTitle();
        this.priority = newTask.getPriority();
        this.deadLine = newTask.getDeadLine();
        this.description = newTask.getDescription();
        this.id = newTask.getId();
        imgSrc = ("/img/edit.png");
        setPriorityNum();
    }
    
    /**
     * The function sets the value of the "id" variable.
     * 
     * @param id The parameter "id" is an integer value that represents the ID of an object.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * The function returns the value of the id variable.
     * 
     * @return The method is returning the value of the variable "id".
     */
    public int getId(){
        return id;
    }

    /**
     * The function converts a priority level enum value to a string representation.
     * 
     * @return The method is returning a string representation of the priority level.
     */
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
    
   /**
    * The function "setPriorityNum" assigns a numerical value to the "priorityNum" variable based on
    * the value of the "priority" variable.
    */
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
    
   /**
    * The function returns the value of the priorityNum variable.
    * 
    * @return The method is returning the value of the variable "priorityNum".
    */
    public int getPriorityNum(){
        return priorityNum;
    }
    

    /**
     * The getTitle() function returns the title of an object.
     * 
     * @return The method is returning the value of the variable "title".
     */
    public String getTitle() {
        return title;
    }

    /**
     * The function sets the title of an object.
     * 
     * @param title The parameter "title" is a String that represents the new title for an object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The getDescription() function returns the description of an object.
     * 
     * @return The method is returning a String value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The function sets the description of an object.
     * 
     * @param description The parameter "description" is a String that represents the description of an
     * object.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The function sets the deadline for a task.
     * 
     * @param d The parameter "d" is of type LocalDate, which represents a date without a time
     * component.
     */
    public void setDeadLine(LocalDate d){
        this.deadLine = d;
    }
    
    /**
     * The function returns the deadline as a LocalDate object.
     * 
     * @return The method is returning a LocalDate object.
     */
    public LocalDate getDeadLine(){
        return deadLine;
    }
    
    /**
     * The function returns the deadline as a string.
     * 
     * @return The method is returning the string representation of the "deadLine" object.
     */
    public String getDeadLineToString(){
        return deadLine.toString();
    }
    
    /**
     * The function returns the value of the imgSrc variable.
     * 
     * @return The method is returning the value of the variable "imgSrc".
     */
    public String getImgSrc(){
        return imgSrc;
    }
    
    /**
     * The function sets the priority level of an object and updates its priority number.
     * 
     * @param priority The priority parameter is of type PriorityLevel.
     */
    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
        setPriorityNum();
    }

    /**
     * The function returns the priority level of an object.
     * 
     * @return The method is returning the value of the variable "priority" of type PriorityLevel.
     */
    public PriorityLevel getPriority() {
        return priority;
    }

    /**
     * The compareTo function compares the priority numbers of two Task objects and returns 1 if the
     * current object has a higher priority, -1 if it has a lower priority, and 0 if the priorities are
     * equal.
     * 
     * @param o The parameter "o" is of type Object, which means it can accept any object as an
     * argument. In this case, it is being cast to a Task object using the line "Task taskCompare =
     * (Task)o;".
     * @return The method is returning an integer value that represents the comparison result between
     * the current Task object and the object passed as a parameter. The possible return values are:
     * - 1: if the current Task object has a higher priority number than the object being compared to.
     * - 0: if the current Task object has the same priority number as the object being compared to.
     * - -1: if the current
     */
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
    
    /**
     * The function compares the deadline of a task with the deadline of another task and returns 1 if
     * the first task's deadline is after the second task's deadline, -1 if it is before, and 0 if they
     * are the same.
     * 
     * @param o The parameter "o" is an object that is being compared to the current object. In this
     * case, it is being casted to a Task object.
     * @return The method is returning an integer value.
     */
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
