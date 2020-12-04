//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DialogController.java
//  Abstract controller class for dialog controllers.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class DialogController<T>  implements Initializable
{
    protected Database database = null;
    protected Stage stage = null;

    @FXML protected Button addButton;

    /**
     * Gets the value this dialog should return.
     * (e.g. a dialog to create a new Customer should return the resulting
     * Customer instance)
     * @return
     */
    public abstract T getValue();

    /**
     * Initializes the controller.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Add button is disabled while fields are empty
        addButton.setDisable(true);
    }

    /**
     * Called after dialog is closed using window close button.
     */
    protected void onCloseRequest() {}

    /**
     * Called after a field changes.
     * Should be called by listeners.
     */
    protected void onFieldChanged() {
        addButton.setDisable(!validateFields());
    }

    /**
     * Sets the database instance available to this dialog.
     * @param database
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Sets the stage instance available to this dialog.
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;

        //  Clear fields if the dialog close button is used
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                onCloseRequest();
            }
        });
    }

    /**
     * Sets the value this dialog should return.
     * @param value
     */
    public void setValue(T value) {}

    /**
     * Checks if all fields required to create a new database object are valid.
     * @return true if fields are all valid, false if fields are invalid
     */
    protected abstract boolean validateFields();
}
