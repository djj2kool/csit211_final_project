//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddEmployeeDialogController.java
//  Controller for new employee dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddEmployeeDialogController extends EmployeeDialogController
{
    /**
     * Called after the add button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        createEmployee();
    }
}
