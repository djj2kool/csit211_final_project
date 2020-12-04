//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditDialog.java
//  Generic class for dialogs that edit existing database objects.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

abstract class EditDialog<T>
{
    protected T value = null;
    protected DialogController<T> controller = null;
    private Stage dialogStage = null;

    //  ------------------------------------------------------------------------
    public EditDialog(
        String title,
        String fxml,
        Database database,
        T value
    ) {
        FXMLLoader loader = null;
        Parent root       = null;
        Scene scene       = null;

        this.value = value;

        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            root = loader.load();
            scene = new Scene(root);

            dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.DECORATED);
            dialogStage.setScene(scene);

            controller = loader.getController();
            controller.setDatabase(database);
            controller.setStage(dialogStage);
            controller.setValue(value);
        }
        catch (Exception ex) {
        }
    }

    //  ------------------------------------------------------------------------
    public void show() {
        dialogStage.show();
        dialogStage.requestFocus();
    }

    //  ------------------------------------------------------------------------
    public void showAndWait() {
        dialogStage.showAndWait();
    }
}
