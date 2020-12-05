//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Dialog.java
//  Generic class for dialog boxes.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialog<T>
{
    protected DialogController<T> controller = null;
    private Stage dialogStage = null;

    /**
     * Constructs a dialog.
     * @param title the title to display in the dialog titlebar
     * @param fxml FXML
     * @param database
     * @param modal true if modal, false if modeless
     */
    public Dialog(
        String title,
        String fxml,
        Database database,
        boolean modal
    ) throws Exception {
        FXMLLoader loader = null;
        Parent root       = null;
        Scene scene       = null;

        loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        scene = new Scene(root);

        dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(
            modal ? Modality.APPLICATION_MODAL : Modality.NONE);
        dialogStage.initStyle(StageStyle.DECORATED);
        dialogStage.setScene(scene);

        controller = loader.getController();
        controller.setDatabase(database);
        controller.setStage(dialogStage);
    }

    /**
     * Gets the database record this dialog interacts with.
     * @return
     */
    public T getValue() {
        return controller.getValue();
    }

    /**
     * Whether or not this dialog is currentlyopen.
     * @return
     */
    public boolean isShowing() {
        return dialogStage.isShowing();
    }

    /**
     * Gets the optional database record this dialog added.
     * @return
     */
    public Optional<T> result() {
        T value = controller.getValue();
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    /**
     * Sets the database record this dialog interacts with.
     * @param value
     */
    public void setValue(T value) {
        controller.setValue(value);
    }

    /**
     * Shows and focuses this dialog box.
     */
    public void show() {
        dialogStage.show();
        dialogStage.requestFocus();
    }

    /**
     * Shows dialog box and then waits for user to close it before returning.
     */
    public void showAndWait() {
        dialogStage.showAndWait();
    }
}
