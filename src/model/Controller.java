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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;



//Normal
import util.HashTable;

public class Controller implements Initializable{
    
    
    @FXML
    private VBox tasksLayout;
    
    @FXML
    private Button addTask;

    @FXML
    private Button backButton;
    
    @FXML
    private Button filterTasks;
    @FXML
    private Button finishPriority;
    @FXML
    private Button finishNonPriority;
    
    private int taskIdCount;
    
    //NORMAL
    private HashTable tasks;
   



    public Controller(){
        tasks = new HashTable();
    }

    public void handleTaskEdit(Task task){
        
        tasks.modify(task);
    }
    
    public void handleTaskDelete(HBox hbox, Task task) {
        
        tasksLayout.getChildren().remove(hbox);
        tasks.remove(task);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
            newTask.setId(taskIdCount);
            taskIdCount++;
            
            if(newTask != null){
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));
                
                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                tic.initAttributes(newTask);
                tasksLayout.getChildren().add(hBox);
                tic.setParent(this);
                
                tasks.add(newTask);
                
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

    @FXML
    private void finishPriorityTask(ActionEvent event) {
    }

    @FXML
    private void finishNonPriorityTask(ActionEvent event) {
    }
    

    
 
    
    

}
