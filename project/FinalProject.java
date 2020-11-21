//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  FinalProject.java
//  Application entry.
//  Visual Studio Code

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalProject extends Application
{
    //  ------------------------------------------------------------------------
    public static void main(String[] args) {
        launch(args);
    }

    //  ------------------------------------------------------------------------
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CDLR Corporation - Vehicle Rentals");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
