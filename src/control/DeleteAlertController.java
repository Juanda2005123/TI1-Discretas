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
    
    public void initAttributes(Task task) {
        title.setText(task.getTitle());
        delete = false;
    }

    @FXML
    private void cancel(ActionEvent event) {
        delete = false;
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void delete(ActionEvent event) {
        delete = true;
        Stage stage = (Stage) this.deleteButton.getScene().getWindow();
        stage.close();
    }
    
    public boolean isDelete(){
        return delete;
    }
    
    
}
