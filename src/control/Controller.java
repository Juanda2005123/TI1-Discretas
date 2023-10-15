package control;

//Interfaz Grafica
import exceptions.EmptyListException;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataType;
import model.PriorityLevel;
import model.Task;
import model.Trio;
import model.Undo;
import util.FifoLinkedList;
import util.Stack;


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
    private Stack<Undo> undoData;
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
        undoData = new Stack<>();
        tasksId = 0;
        
    }
    
    @FXML
    public void undoAction(ActionEvent event) {
        Undo temp = undoData.pop();
        if(temp != null){
            if(null != temp.getDataType())switch (temp.getDataType()) {
                case DELETE:{
                    Task task = (Task) temp.getData();
                    addTaskUndo(task);
                        break;
                    }
                case MODIFY:
                    Trio trio = (Trio) temp.getData();
                    Task newTask = (Task) trio.getNewTask();
                    Task oddTask = (Task) trio.getOddTask();
                    
                    handleTaskEditUndo(oddTask, newTask);
                        break;
                case ADD:{
                    Task task = (Task)temp.getData();
                    handleTaskDeleteUndo(task);
                        break;
                    }
                default:
                    break;
            }
            
        }
    }
    
    public void handleTaskEdit(Task task, Task oldOne){
        Trio trio =  new Trio(task, oldOne);
        Undo data = new Undo(trio, DataType.MODIFY);

        undoData.push(data);

        modifyTaskToStructures(task, oldOne);
        filterTasksInside();
    }

    public void handleTaskEditUndo(Task task, Task oldOne){
                            
        modifyTaskToStructures(task, oldOne);
        filterTasksInside();
    }
    
    public void handleTaskDelete(Task task) {
        Undo data = new Undo(task, DataType.DELETE);
        undoData.push(data);
        
        removeTaskToStructures(task);
        filterTasksInside();
    }

    public void handleTaskDeleteUndo(Task task) {
        
        removeTaskToStructures(task);
        filterTasksInside();
    }

    public void addTaskUndo(Task newTask){
        
        addTaskToStructures(newTask);
        filterTasksInside();


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
            
            
            if(newTask != null){
                               
                newTask.setId(tasksId);
                tasksId++;
                
                addTaskToStructures(newTask);
                filterTasksInside();
                Undo data = new Undo(newTask, DataType.ADD);
                undoData.push(data);
              
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
        try{
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
        } catch(EmptyListException e){
            e.printStackTrace();
        }
        
            
    }
    
    private void organizeByDeadLine() throws IOException{
        try{
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
        } catch(EmptyListException e){
            e.printStackTrace();
        }
        
            
    }

    @FXML
    private void finishPriorityTask(ActionEvent event) {
        try{
            if(showANDCompleteByPriority.getMax().getPriority()==PriorityLevel.NON){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("No more tasks");
                alert.setContentText("There are not more tasks to finish by Priority");
                alert.showAndWait();
            } else {
                Task task = showANDCompleteByPriority.extractMaxPriority();
                //REMOVE IN STRUCTURES
                tasks.remove(task);//HashTable
                //show tasks
                showByDeadLine.removeDeadLine(task);
                //Complete tasks
                completeNonPriorityTask.remove(task);
                //REMOVE IN STRUCTURES
                
                Undo data = new Undo(task, DataType.DELETE);
                undoData.push(data);
                
                filterTasksInside();
            }
        } catch(EmptyListException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No more tasks");
            alert.setContentText("There are not more tasks to finish by Priority");
            alert.showAndWait();
        }

    }

    @FXML
    private void finishNonPriorityTask(ActionEvent event) {
        try{
            Task task = completeNonPriorityTask.peek();
            completeNonPriorityTask.dequeue();
            
            //REMOVE IN STRUCTURES
            tasks.remove(task);//HashTable
            //Show and complete tasks
            showANDCompleteByPriority.removePriority(task);
            //show tasks
            showByDeadLine.removeDeadLine(task);
            //REMOVE IN STRUCTURES
            
            Undo data = new Undo(task, DataType.DELETE);
            undoData.push(data);
            
            filterTasksInside();
            
        } catch(EmptyListException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No more tasks");
            alert.setContentText("There are not more tasks to finish by Non-Priority");
            alert.showAndWait();
        }
        
        
    }
    
    private void addTaskToStructures(Task task){
        tasks.add(task);//HashTable
        
        //Show and complete tasks
        showANDCompleteByPriority.insert(task); 
        showANDCompleteByPriority.shiftUpPriority(showANDCompleteByPriority.getSize());
        
        //show tasks
        showByDeadLine.insert(task);
        showByDeadLine.shiftUpDeadLine(showByDeadLine.getSize());
        
        if(task.getPriority()==PriorityLevel.NON){
            //Complete tasks
            completeNonPriorityTask.enqueue(task);
        }

    }
    
    public void removeTaskToStructures(Task task){
        try {
            tasks.remove(task);//HashTable
            
            //Show and complete tasks
            showANDCompleteByPriority.removePriority(task);
            
            //show tasks
            showByDeadLine.removeDeadLine(task);
            if(task.getPriority()==PriorityLevel.NON){
                //Complete tasks
                completeNonPriorityTask.remove(task);
            }
        } catch(EmptyListException e){
            e.printStackTrace();
        }
                
    }
    
    public void modifyTaskToStructures(Task task, Task oldOne){
        try {
                     
            tasks.modify(task);//HashTable
            
            //Show and complete tasks
            showANDCompleteByPriority.modifyPriority(task);
            
            //show tasks
            showByDeadLine.modifyDeadLine(task);
            
            if(oldOne.getPriority()==PriorityLevel.NON){
                if(task.getPriority()==PriorityLevel.NON){
                    //Complete tasks
                    completeNonPriorityTask.modify(task);
                } else {
                    completeNonPriorityTask.remove(task);
                }
                
            } else if(task.getPriority()==PriorityLevel.NON){
                completeNonPriorityTask.enqueue(task);
            }
        } catch(EmptyListException e){
            e.printStackTrace();
        }
        
    }

    

    
 
    
    

}
