package controllerVista;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
/**
 *
 * @author Juan David Quintero
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/TasksVista.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("PRUEBA");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
