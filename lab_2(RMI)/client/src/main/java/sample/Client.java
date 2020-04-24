package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <b>Launcher of Main application UI window for client</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class Client extends Application {

    /**
     * Method initialize sample.fxml UI design and set to the root
     * @param primaryStage is stage to display
     * @throws Exception can be thrown by loader.load()
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Products and warehouses");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Entry point into application, launch UI
     * @param args query params from command line
     */
    public static void main(String[] args) {
        launch(args);
    }
}
