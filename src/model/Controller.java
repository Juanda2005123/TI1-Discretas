package model;

//Interfaz Grafica
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;




//Normal
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
//import util.HashTable;

public class Controller implements Initializable{
    
    
    @FXML
    private VBox tasksLayout;
    
    //private HashTable tasks;



    public Controller(){
        //tasks = new HashTable();
    }

    public String showMenu(){
        String msg = "(0) Exit";
        msg += "(1) Add task";
        msg += "(4) Ctrl z";
        return msg;
    }

    /**
    public void addTask(String title, String description, int day, int month, int year, boolean priority){
        Calendar c = new GregorianCalendar(year, month, day);
        Task newTask = new Task(title, description, c, priority);
        tasks.add(newTask);
    }
    */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Task> tasks = tasks2();
        for(int i = 0; i < tasks.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));
            
            
            try{
                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                tic.setData(tasks.get(i));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    
    private ArrayList<Task> tasks2(){
        
        ArrayList<Task> tasks2 = new ArrayList<>();

        
        Task newTask = new Task("Integradora", "Es una tarea interesante", "22-09-2023", true);
        tasks2.add(newTask);
        
        Task newTask2 = new Task("Parcial Software", "Es para la otra semana", "30-11-2024", true);
        tasks2.add(newTask2);
        
        return tasks2;
        
    }
    
    

}
