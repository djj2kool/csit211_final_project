//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  App.java
//  Application class. JavaFX entry point.
//  Eclipse, Visual Studio Code

package finalProject;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
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
     * Displays an alert box for exceptions thrown.
     */
	public static void showErrorAlert(Exception ex) {
        Alert alert = null;
        TextArea textArea = null;
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        alert = new Alert(Alert.AlertType.ERROR);

        //  Set title based on exception type
        if (ex instanceof DatabaseException) {
            alert.setTitle("Database Transaction Error");
        } else {
            alert.setTitle("Error");
        }

        alert.setHeaderText("An exception occurred.");

        //  Exception message
        alert.setContentText(ex.getMessage());

        //  Exception stacktrace
        ex.printStackTrace(pw);

        //  Display exception stacktrace in text area
        textArea = new TextArea(sw.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane pane = new GridPane();
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.add(textArea, 0, 0);

        alert.getDialogPane().setExpandableContent(pane);

        alert.showAndWait();
	}

    /**
     * {@inheritDoc}
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
