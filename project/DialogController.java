//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DialogController.java
//  Abstract controller class for dialog controllers.
//  Visual Studio Code

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class DialogController<T> {
    protected Database database = null;
    protected Stage stage = null;

    /**
     * Gets the value this dialog should return.
     * (e.g. a dialog to create a new Customer should return the resulting
     * Customer instance)
     * @return
     */
    public abstract T getValue();

    /**
     * Called after dialog is closed using window close button.
     */
    protected void onCloseRequest() {}

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
    public void setValue(T value) {
    }
}
