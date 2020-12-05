//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialogController.java
//  Controller for rental edit dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditRentalDialogController extends RentalDialogController
{
    /**
     * Called when the submit button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        editRental();
    }
}
