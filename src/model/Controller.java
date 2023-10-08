package model;

//Interfaz Grafica
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import util.DoubleLinkedNode;
import util.FifoLinkedList;



//Normal
import util.HashTable;
import util.PriorityQueue;

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
    
    
    
    //NORMAL
    private HashTable<Task> tasks;
    private PriorityQueue showANDCompleteByPriority;
    private PriorityQueue showByDeadLine;
    private FifoLinkedList<Task> completeNonPriorityTask;
    
    private int tasksId;
   

    public Controller(){
        //Tasks
        tasks = new HashTable<>();
        //Show and complete tasks
        showANDCompleteByPriority = new PriorityQueue();
        //show tasks
        showByDeadLine = new PriorityQueue();
        //Complete tasks
        completeNonPriorityTask = new FifoLinkedList<>();
        
        tasksId = 0;
        
    }

    public void handleTaskEdit(Task task){
        
        modifyTaskToStructures(task);
    }
    
    public void handleTaskDelete(HBox hbox, Task task) {
        
        tasksLayout.getChildren().remove(hbox);
        removeTaskToStructures(task);
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
            newTask.setId(tasksId);
            tasksId++;
            
            if(newTask != null){
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));
                
                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                tic.initAttributes(newTask);
                tasksLayout.getChildren().add(hBox);
                tic.setParent(this);
                
                addTaskToStructures(newTask);
                
                filterTasksInside();
                
                
            }
            
            
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private void filterTasksInside(){
        try{
            tasksLayout.getChildren().clear();
            
            if(filterTasks.getText().equalsIgnoreCase("DeadLine")){

                organizeByDeadLine();

            } else {

                organizeByPriority();
                
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void filterTasks(ActionEvent event){
    
        try{
            tasksLayout.getChildren().clear();
            
            if(filterTasks.getText().equalsIgnoreCase("DeadLine")){
                
                organizeByPriority();
                filterTasks.setText("Priority");
            } else {
                

                organizeByDeadLine();
                filterTasks.setText("DeadLine");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void organizeByPriority() throws IOException{
        tasksLayout.getChildren().clear();
            PriorityQueue p = new PriorityQueue(showANDCompleteByPriority);
            System.out.println(showANDCompleteByPriority.getSize());
            while(p.getSize()>=0){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));

                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                
                tic.initAttributes(p.extractMaxPriority());
                tasksLayout.getChildren().add(hBox);
                tic.setParent(this);
            }
            
    }
    
    private void organizeByDeadLine() throws IOException{
        tasksLayout.getChildren().clear();
            PriorityQueue p = new PriorityQueue(showByDeadLine);
            System.out.println(p.getSize());
            while(p.getSize()>=0){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vista/task_item.fxml"));

                HBox hBox = fxmlLoader.load();
                Task_itemController tic = fxmlLoader.getController();
                
                tic.initAttributes(p.extractMaxDeadLine());
                tasksLayout.getChildren().add(hBox);
                tic.setParent(this);
            }
            
    }

    @FXML
    private void finishPriorityTask(ActionEvent event) {
        
    }

    @FXML
    private void finishNonPriorityTask(ActionEvent event) {
        
    }
    
    private void addTaskToStructures(Task task){
        tasks.add(task);//HashTable
        
        //Show and complete tasks
        showANDCompleteByPriority.insert(task); 
        showANDCompleteByPriority.shiftUpPriority(showANDCompleteByPriority.getSize());
        
        //show tasks
        showByDeadLine.insert(task);
        showByDeadLine.shiftUpDeadLine(showByDeadLine.getSize());
        
        //Complete tasks
        completeNonPriorityTask.enqueue(task);
    }
    
    public void removeTaskToStructures(Task task){
        tasks.remove(task);//HashTable
        
        //Show and complete tasks
        showANDCompleteByPriority.removePriority(task);
        
        //show tasks
        showByDeadLine.removeDeadLine(task);
        
        //Complete tasks
        completeNonPriorityTask.remove(task);
    }
    
    public void modifyTaskToStructures(Task task){
        tasks.modify(task);//HashTable
        
        //Show and complete tasks
        showANDCompleteByPriority.modifyPriority(task);
        
        //show tasks
        showByDeadLine.modifyDeadLine(task);
        
        //Complete tasks
        completeNonPriorityTask.modify(task);
    }

    
 
    
    

}
