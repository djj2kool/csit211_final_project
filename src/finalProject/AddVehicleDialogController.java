//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialogController.java
//  Controller for new vehicle dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddVehicleDialogController extends VehicleDialogController
{
    @FXML
    private void onAddButton(ActionEvent event) {
        createVehicle();
    }
}
