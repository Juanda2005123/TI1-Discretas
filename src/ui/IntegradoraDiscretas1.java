package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Juan David Quintero
 * @author Juan Andrés Cano R
 */
public class IntegradoraDiscretas1 extends Application {
    
    /**
     * This function sets up and displays the main tasks window in a JavaFX application.
     * 
     * @param primaryStage The primary stage is the main window of the JavaFX application. It
     * represents the top-level container for all the UI components.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Parent root = FXMLLoader.load(getClass().getResource("/vista/tasksMain.fxml"));
        
        primaryStage.setTitle("Tasks");
        primaryStage.setScene(new Scene(root, 500, 800));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
    
}
