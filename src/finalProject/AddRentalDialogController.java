//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialogController.java
//  Controller for new rental dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddRentalDialogController extends RentalDialogController
{
    /**
     * Called after the add button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        createRental();
    }
}
