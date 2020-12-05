//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditEmployeeDialogController.java
//  Controller for employee edit dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditEmployeeDialogController extends EmployeeDialogController
{
    /**
     * Called after the add button is activated
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        editEmployee();
    }
}
