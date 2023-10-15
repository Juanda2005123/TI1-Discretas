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
   

   
    // The above code is defining a Java class called "Controller". Inside the constructor of the
    // class, it initializes several data structures for managing tasks.
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
    
   /**
    * The `undoAction` function is used to undo the last action performed by popping the last action
    * from the `undoData` stack and performing the appropriate action based on the type of data stored
    * in the action.
    * 
    * @param event The event parameter is an ActionEvent object that represents the action that
    * triggered the undoAction method.
    */
    @FXML
    public void undoAction(ActionEvent event) {
        try {
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
        } catch (EmptyListException ex) {            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("No more actions");
            alert.setContentText("No more actions to undo");
            alert.show();
        }
    }
    
    /**
     * The function handles the editing of a task by creating an undo data object, pushing it onto a
     * stack, modifying the task in data structures, and filtering the tasks.
     * 
     * @param task The "task" parameter represents the updated version of the task that needs to be
     * edited.
     * @param oldOne The "oldOne" parameter is the original version of the task that is being edited.
     * It is used to compare the changes made to the task and update any relevant data structures or
     * filters.
     */
    public void handleTaskEdit(Task task, Task oldOne){
        Trio trio =  new Trio(task, oldOne);
        Undo data = new Undo(trio, DataType.MODIFY);

        undoData.push(data);

        modifyTaskToStructures(task, oldOne);
        filterTasksInside();
    }

    /**
     * The function "handleTaskEditUndo" modifies a task and filters tasks inside a data structure.
     * 
     * @param task The task parameter represents the updated version of the task that needs to be
     * edited.
     * @param oldOne The old version of the task that needs to be undone.
     */
    public void handleTaskEditUndo(Task task, Task oldOne){
                            
        modifyTaskToStructures(task, oldOne);
        filterTasksInside();
    }
    
    /**
     * The function handles the deletion of a task by creating an undo data object, removing the task
     * from data structures, and filtering the remaining tasks.
     * 
     * @param task The task object that needs to be deleted.
     */
    public void handleTaskDelete(Task task) {
        Undo data = new Undo(task, DataType.DELETE);
        undoData.push(data);
        
        removeTaskToStructures(task);
        filterTasksInside();
    }

   /**
    * The function handles the deletion and undoing of a task by removing it from data structures and
    * filtering the remaining tasks.
    * 
    * @param task The task object that needs to be deleted and undone.
    */
    public void handleTaskDeleteUndo(Task task) {
        
        removeTaskToStructures(task);
        filterTasksInside();
    }

    /**
     * The addTaskUndo function adds a new task to a data structure and then filters the tasks inside.
     * 
     * @param newTask The new task that needs to be added to the structures.
     */
    public void addTaskUndo(Task newTask){
        
        addTaskToStructures(newTask);
        filterTasksInside();


    }


    /**
     * The initialize function is called when the controller is initialized and can be used to set up
     * initial values or perform any necessary setup tasks.
     * 
     * @param location The location parameter represents the URL of the FXML file that is being loaded.
     * It specifies the location of the FXML file on the file system or on the web.
     * @param resources The ResourceBundle object that contains the resources for the specified
     * location. These resources can be used to localize the application.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * The addTaskAction function opens a new window for adding a new task, retrieves the task
     * information from the window, assigns an ID to the task, adds the task to data structures,
     * filters the tasks, and adds an undo data entry.
     * 
     * @param event The event that triggered the action, such as a button click.
     */
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
    
    /**
     * The function clears the tasks layout and then organizes the tasks either by deadline or by
     * priority.
     */
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
    
   /**
    * The function filters and organizes tasks based on either deadline or priority.
    * 
    * @param event The event parameter is an ActionEvent object that represents the event that
    * triggered the method call. It contains information about the event, such as the source of the
    * event and any additional data associated with it.
    */
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
    
    /**
     * The function organizes tasks by priority and displays them in a JavaFX layout.
     */
    private void organizeByPriority() throws IOException{
        try{
            tasksLayout.getChildren().clear();
                PriorityQueue p = new PriorityQueue(showANDCompleteByPriority);
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
    
    /**
     * The function organizes tasks by their deadline and displays them in a priority queue.
     */
    private void organizeByDeadLine() throws IOException{
        try{
            tasksLayout.getChildren().clear();
            PriorityQueue p = new PriorityQueue(showByDeadLine);
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
/**
 * This function finishes a priority task by removing it from various data structures and updating the
 * UI.
 * 
 * @param event The event parameter is an ActionEvent object that represents the event that triggered
 * the method.
 */

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

   /**
    * This function removes and completes a non-priority task from various data structures and adds it
    * to an undo stack.
    * 
    * @param event The event parameter is an ActionEvent object that represents the event that
    * triggered the method. It is typically used to handle user interactions, such as button clicks.
    */
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
    /**
     * The function adds a task to various data structures based on its priority and deadline.
     * 
     * @param task The "task" parameter is an object of the Task class.
     */
    
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
    /**
     * The function removes a task from various data structures and handles exceptions.
     * 
     * @param task The task object that needs to be removed from the data structures.
     */
    
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
    
    /**
     * The function modifies a task by updating it in a hash table, modifying its priority in a
     * priority queue, modifying its deadline in a binary search tree, and updating its completion
     * status in a queue.
     * 
     * @param task The task object that needs to be modified and updated in the data structures.
     * @param oldOne The old task that needs to be modified.
     */
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
