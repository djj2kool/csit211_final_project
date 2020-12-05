//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  App.java
//  Application class. JavaFX entry point.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application
{
    /**
     * Application primary stage.
     * Used as owner of child dialog boxes so application exits correctly
     * if there are modeless dialogs active when closing the main window.
     */
    public static Stage primaryStage;

    /**
     * Displays an alert box for exceptions thrown during database operations.
     */
    public static void showDatabaseErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Transaction Error");
        alert.setContentText(ex.getMessage());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * The main entry point for all JavaFX applications. The start method is
     * called after the init method has returned, and after the system is ready
     * for the application to begin running.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane   = null;
        Scene scene = null;

        App.primaryStage = primaryStage;

        primaryStage.setTitle("CDLR Corporation - Vehicle Rentals");
        pane = (Pane)FXMLLoader.load(getClass().getResource("App.fxml"));
        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
