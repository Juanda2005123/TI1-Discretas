/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Task;

/**
 * FXML Controller class
 *
 * @author Juan David Quintero
 */
public class DeleteAlertController implements Initializable {
    
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label title;
    
    private boolean delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * The function initializes the attributes of a Task object by setting the title attribute and
     * setting the delete attribute to false.
     * 
     * @param task The task object that contains the information about the task, such as its title.
     */
    public void initAttributes(Task task) {
        title.setText(task.getTitle());
        delete = false;
    }

    /**
     * The cancel function closes the current stage without deleting anything.
     * 
     * @param event The event parameter is an instance of the ActionEvent class. It represents the
     * event that triggered the method call, in this case, a button click event.
     */
    @FXML
    private void cancel(ActionEvent event) {
        delete = false;
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }
/**
 * The delete function sets a boolean variable to true and closes the current window.
 * 
 * @param event The event parameter is an object that represents the event that triggered the method.
 * In this case, it is an ActionEvent, which is typically generated when a user interacts with a GUI
 * component, such as clicking a button.
 */

    @FXML
    private void delete(ActionEvent event) {
        delete = true;
        Stage stage = (Stage) this.deleteButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * The function returns a boolean value indicating whether the delete operation is allowed.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean isDelete(){
        return delete;
    }
    
    
}
