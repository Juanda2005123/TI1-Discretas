/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Task;


/**
 * FXML Controller class
 *
 * @author Juan David Quintero
 */
public class Task_itemController implements Initializable {
    


    @FXML
    private Label deadLine;

    @FXML
    private ImageView editButtonImg;

    @FXML
    private Label prioritary;

    @FXML
    private Label title;
    @FXML
    private Button editButton;
    
    private Task task;
    @FXML
    private HBox hBox;
    @FXML
    private Button deleteButton;

    
    private Controller parent;
    
    
    /**
     * The function sets the parent controller for an object.
     * 
     * @param p The parameter "p" is of type "Controller" and represents the parent controller object.
     */
    public void setParent(Controller p){
        this.parent = p;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /**
     * The function initializes the attributes of a task by setting the image, title, priority, and
     * deadline.
     * 
     * @param taskito The parameter "taskito" is of type "Task", which is an object representing a
     * task.
     */
    public void initAttributes(Task taskito){
        
        this.task = taskito;
        
        Image img = new Image(getClass().getResourceAsStream(task.getImgSrc()));
        
        editButtonImg.setImage(img);
        
        title.setText(task.getTitle());
        prioritary.setText(task.priorityToString());
        deadLine.setText(task.getDeadLineToString());
        
        
    }

    /**
     * This function opens a new window for editing a task and updates the task's information in the
     * main window.
     * 
     * @param event The event that triggered the method, typically a button click or key press event.
     */
    @FXML
    private void editTask(ActionEvent event){ 
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/newModifyTask.fxml"));
            
            Parent root = loader.load();
            NewModifyTaskController controllerEdit = loader.getController();
            controllerEdit.initAttributes(task);
            
            Task oldOne = new Task(task);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            
            task = controllerEdit.getTask();
            
            title.setText(task.getTitle());
            prioritary.setText(task.priorityToString());
            deadLine.setText(task.getDeadLineToString());
            
            if(parent != null){
                
                parent.handleTaskEdit(task, oldOne);
            }
            
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteTask(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/deleteAlert.fxml"));
            
            Parent root = loader.load();
            DeleteAlertController cDelete = loader.getController();
            
            cDelete.initAttributes(task);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            
            boolean delete = cDelete.isDelete();
            
            if(delete&&(parent != null)){
                parent.handleTaskDelete(task);
            }
                
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    
    /**
     * The function "getTask" returns the task object.
     * 
     * @return The method is returning a Task object.
     */
    public Task getTask(){
        return task;
    }
        
}
    
