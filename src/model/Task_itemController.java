/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


import java.util.Calendar;
import java.util.GregorianCalendar;


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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setData(Task task){
        
        Image img = new Image(getClass().getResourceAsStream(task.getImgSrc()));
        
        editButtonImg.setImage(img);
        
        title.setText(task.getTitle());
        prioritary.setText(task.getPriority());
        deadLine.setText(task.getDead());
        
        
    }
        
}
    
