package integradoradiscretas1;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Juan David Quintero
 */
public class IntegradoraDiscretas1 extends Application {
    
    
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
