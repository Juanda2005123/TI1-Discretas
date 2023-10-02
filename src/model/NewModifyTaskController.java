/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private String[] options = {"Prioritary", "Non-priority"};
    
    @FXML
    private DatePicker deadLinePicker;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private Button submitButton;
    
    private Task task;
    @FXML
    private Button exitButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        priorityChoiceBox.getItems().addAll(options);
        
    }    
    
    public void initAttributes(Task taskito){
        this.task = taskito;
        
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        
        if(task.isPriority()){
            priorityChoiceBox.setValue(options[0]);
        } else {
            priorityChoiceBox.setValue(options[1]);
        }
        
        deadLinePicker.setValue(task.getDeadLine());
    }


    @FXML
    private void submitData(ActionEvent event) {
        String newTitle = this.title.getText();
        String newDescription = this.description.getText();        
        boolean priority = (priorityChoiceBox.getValue().equals(options[0]));
        LocalDate deadLine = deadLinePicker.getValue();
        
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
    }
    
    
    public Task getTask(){
        return task;
    }

    @FXML
    private void Exit(ActionEvent event) {
        this.task = null;
        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }
}
