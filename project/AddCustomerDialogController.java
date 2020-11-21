//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialogController.java
//  Controller for new customer dialog.
//  Visual Studio Code

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddCustomerDialogController implements Initializable
{
    Stage stage;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }

    //  ------------------------------------------------------------------------
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
