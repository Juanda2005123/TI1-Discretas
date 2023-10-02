package model;

//Interfaz Grafica
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;




//Normal
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import util.HashTable;

public class Controller implements Initializable{
    
    
    @FXML
    private VBox tasksLayout;
    
    @FXML
    private Button addTask;

    @FXML
    private Button backButton;
    
    @FXML
    private Button filterTasks;
    //private HashTable tasks;



    public Controller(){
        //tasks = new HashTable();
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
                tic.initAttributes(tasks.get(i));
                tasksLayout.getChildren().add(hBox);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @FXML
    public void addTaskAction(ActionEvent event) {
        
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/newModifyTask.fxml"));
            
            Parent root = loader.load();
            NewModifyTaskController controllerAdd = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Task newTask = controllerAdd.getTask();
            
            if(newTask != null){
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));
                
                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                tic.initAttributes(newTask);
                tasksLayout.getChildren().add(hBox);
            }
            
            
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void filterTasks(ActionEvent event) {
        if(filterTasks.getText().equalsIgnoreCase("DeadLine")){
            filterTasks.setText("Priority");
        } else {
            filterTasks.setText("DeadLine");
        }
        
    }
    
    private ArrayList<Task> tasks2(){
        
        LocalDate date = LocalDate.now();
        
        
        
        ArrayList<Task> tasks2 = new ArrayList<>();

        
        Task newTask = new Task("Integradora", "Es una tarea interesante", date, true);
        tasks2.add(newTask);
        
        Task newTask2 = new Task("Parcial Software", "Es para la otra semana", date, true);
        tasks2.add(newTask2);
        
        Task newTask3 = new Task("Parcial teoria", "Es para la otra semana", date, true);
        tasks2.add(newTask3);
        
        return tasks2;
        
    }

    
 
    
    

}
