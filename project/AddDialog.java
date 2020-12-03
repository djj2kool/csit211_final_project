//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddDialog.java
//  Generic class for dialogs that create new database objects.
//  Visual Studio Code

import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

abstract class AddDialog<T>
{
    protected DialogController<T> controller = null;
    private Stage dialogStage = null;

    //  ------------------------------------------------------------------------
    public AddDialog(
        String title,
        String fxml,
        Database database,
        boolean modal
    ) {
        FXMLLoader loader = null;
        Parent root       = null;
        Scene scene       = null;

        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            root = loader.load();
            scene = new Scene(root);

            dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(
                modal ? Modality.APPLICATION_MODAL : Modality.NONE);
            dialogStage.initStyle(StageStyle.DECORATED);
            dialogStage.initOwner(FinalProject.primaryStage);
            dialogStage.setScene(scene);

            controller = loader.getController();
            controller.setDatabase(database);
            controller.setStage(dialogStage);
        }
        catch (Exception ex) {
        }
    }

    //  ------------------------------------------------------------------------
    public Optional<T> result() {
        T value = controller.getValue();
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }

    //  ------------------------------------------------------------------------
    public void show() {
        dialogStage.show();
        dialogStage.requestFocus();
    }

    //  ------------------------------------------------------------------------
    public Optional<T> showAndWait() {
        dialogStage.showAndWait();
        return result();
    }
}
