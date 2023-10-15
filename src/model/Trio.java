package model;

import javafx.scene.layout.HBox;

public class Trio {
    private Task newTask;
    private Task oddTask;
    private HBox tempHBox;

    public Trio(Task newTask, Task oddTask){
        this.newTask = newTask;
        this.oddTask = oddTask;
        this.tempHBox = null;
    }

    public Trio(Task newTask, HBox tempHBox){
        this.newTask = newTask;
        this.tempHBox = tempHBox;
        this.oddTask = null;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }

    public Task getOddTask() {
        return oddTask;
    }

    public void setOddTask(Task oddTask) {
        this.oddTask = oddTask;
    }

    public HBox getTempHBox() {
        return tempHBox;
    }

    public void setTempHBox(HBox tempHBox) {
        this.tempHBox = tempHBox;
    }
}
