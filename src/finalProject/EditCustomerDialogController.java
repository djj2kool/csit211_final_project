//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialogController.java
//  Controller for new customer dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditCustomerDialogController extends CustomerDialogController
{
    /**
     * Called after the add button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        editCustomer();
    }
}
