package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import util.HashTable;

public class Controller {
    
    private HashTable tasks;

    public Controller(){
        tasks = new HashTable();
    }

    public String showMenu(){
        String msg = "(0) Exit";
        msg += "(1) Add task";
        msg += "(4) Ctrl z";
        return msg;
    }

    public void addTask(String title, String description, int day, int month, int year, boolean priority){
        Calendar c = new GregorianCalendar(year, month, day);
        Task newTask = new Task(title, description, c, priority);
        tasks.add(newTask);
    }

}
