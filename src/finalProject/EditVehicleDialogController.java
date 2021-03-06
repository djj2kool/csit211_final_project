//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditVehicleDialogController.java
//  Controller for Vehicle edit dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditVehicleDialogController extends VehicleDialogController
{
    /**
     * Called after the add buttons is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        editVehicle();
    }
}
