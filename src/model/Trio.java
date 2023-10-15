package model;

import javafx.scene.layout.HBox;

public class Trio {
    private Task newTask;
    private Task oddTask;
    private HBox tempHBox;

    // The code snippet is defining a constructor for the `Trio` class that takes two `Task` objects as
    // parameters. Inside the constructor, it assigns the value of the `newTask` parameter to the
    // `newTask` instance variable, assigns the value of the `oddTask` parameter to the `oddTask`
    // instance variable, and sets the `tempHBox` instance variable to `null`.
    public Trio(Task newTask, Task oddTask){
        this.newTask = newTask;
        this.oddTask = oddTask;
        this.tempHBox = null;
    }

    // The code snippet is defining a constructor for the `Trio` class that takes a `Task` object and
    // an `HBox` object as parameters. Inside the constructor, it assigns the value of the `newTask`
    // parameter to the `newTask` instance variable, assigns the value of the `tempHBox` parameter to
    // the `tempHBox` instance variable, and sets the `oddTask` instance variable to `null`. This
    // constructor allows you to create a `Trio` object with a `Task` and an `HBox`, where the
    // `oddTask` is not specified.
    public Trio(Task newTask, HBox tempHBox){
        this.newTask = newTask;
        this.tempHBox = tempHBox;
        this.oddTask = null;
    }

    /**
     * The function returns a new task.
     * 
     * @return The method is returning a Task object.
     */
    public Task getNewTask() {
        return newTask;
    }

    /**
     * The function sets a new task for an object.
     * 
     * @param newTask The newTask parameter is of type Task, which means it is expecting an object of
     * the Task class to be passed as an argument.
     */
    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * The function returns the oddTask object.
     * 
     * @return The method is returning the oddTask object.
     */
    public Task getOddTask() {
        return oddTask;
    }

    /**
     * The function sets the oddTask variable to the provided Task object.
     * 
     * @param oddTask The oddTask parameter is of type Task and represents a task that is considered
     * odd or unusual.
     */
    public void setOddTask(Task oddTask) {
        this.oddTask = oddTask;
    }

    /**
     * The getTempHBox() function returns an HBox object.
     * 
     * @return The method is returning an HBox object.
     */
    public HBox getTempHBox() {
        return tempHBox;
    }

    /**
     * The function sets the value of the tempHBox variable to the provided HBox object.
     * 
     * @param tempHBox The parameter "tempHBox" is of type HBox, which is a JavaFX class representing a
     * horizontal box container. It is used to set the value of the instance variable "tempHBox" in the
     * current class.
     */
    public void setTempHBox(HBox tempHBox) {
        this.tempHBox = tempHBox;
    }
}
