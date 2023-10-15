/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PriorityLevel;
import model.Task;

/**
 * FXML Controller class
 *
 * @author Juan David Quintero
 */
public class NewModifyTaskController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ChoiceBox<String> priorityChoiceBox;
    private String[] options = {"NON PRORITY", "LOW PRIORITY", "MEDIUM PRIORITY", "HIGH PRIORITY", "IMMEDIATE PRIORITY"};
    
    @FXML
    private DatePicker deadLinePicker;
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private Button submitButton;
    
    private Task task;
    @FXML
    private Button exitButton;
    
   /**
    * The initialize function sets up the priority choice box and enables text wrapping for the
    * description field.
    * 
    * @param url The `url` parameter is the location of the FXML file that contains the UI layout for
    * the JavaFX application. It is used to load the FXML file and initialize the UI components.
    * @param rb The parameter "rb" stands for ResourceBundle. It is used to access localized resources,
    * such as strings, images, and other assets, based on the current locale.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        priorityChoiceBox.getItems().addAll(options);
        description.setWrapText(true);
    }    
    
    /**
     * The function initializes the attributes of a task object and sets the values of the title,
     * description, priority, and deadline fields in the user interface.
     * 
     * @param taskito The parameter "taskito" is of type Task, which is an object representing a task.
     */
    public void initAttributes(Task taskito){
        this.task = taskito;
        
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        
        
        priorityChoiceBox.setValue(task.priorityToString());
        
        
        deadLinePicker.setValue(task.getDeadLine());
    }

/**
 * The submitData function checks if all fields are filled out and if the deadline is after the current
 * date, and then creates or updates a task accordingly.
 * 
 * @param event The event parameter is of type ActionEvent and represents the event that triggered the
 * method. It is typically used to handle user interactions, such as button clicks.
 */

    @FXML
    private void submitData(ActionEvent event) {
        LocalDate now = LocalDate.now();
        
        String newTitle = this.title.getText();
        String newDescription = this.description.getText();        
        LocalDate deadLine = deadLinePicker.getValue();
        
        
        if(newTitle.equals("")||(newDescription.equals(""))||(priorityChoiceBox.getValue()==null)||(deadLinePicker.getValue()==null)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Invalid input");
            alert.setContentText("All fields must be filled out");
            alert.showAndWait();
        } else {
            if(deadLinePicker.getValue().isAfter(now.plusDays(-1))){
                
                PriorityLevel priority = stringToPriorityLevel(priorityChoiceBox.getValue());

                Task newTask = new Task(newTitle, newDescription, deadLine, priority);

                if(this.task!=null){
                    this.task.setTitle(newTitle);
                    this.task.setDescription(newDescription);
                    this.task.setPriority(priority);
                    this.task.setDeadLine(deadLine);

                } else {
                    this.task = newTask;
                }

                Stage stage = (Stage) this.submitButton.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid date");
                alert.setContentText("The date is prior to the current date");
                alert.showAndWait();
            }
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
/**
 * The Exit function closes the current stage/window.
 * 
 * @param event The event parameter is an ActionEvent object that represents the event that triggered
 * the method.
 */

    @FXML
    private void Exit(ActionEvent event) {
        this.task = null;
        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * The function converts a string input into a corresponding PriorityLevel enum value.
     * 
     * @param msg The `msg` parameter is a string that represents the priority level.
     * @return The method is returning a PriorityLevel object.
     */
    private PriorityLevel stringToPriorityLevel(String msg){
        PriorityLevel p = PriorityLevel.NON;
        if(msg.equals(options[1])){
            p = PriorityLevel.LOW;
        } else if(msg.equals(options[2])){
            p = PriorityLevel.MEDIUM;
        } else if(msg.equals(options[3])){
            p = PriorityLevel.HIGH;
        } else if(msg.equals(options[4])){
            p = PriorityLevel.IMMEDIATE;
        }
        return p;
    }
}
