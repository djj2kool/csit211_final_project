//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialogController.java
//  Controller for rental edit dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class EditRentalDialogController extends RentalDialogController
{
    @FXML private CheckBox closedCheckBox;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        closedCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            changeRentalStatus();
            onFieldChanged();
        });

        populateMileage(0);
    }

    /**
     * Called when the submit button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        editRental();
    }

    /**
     * Populates the closed checkbox.
     * @param customer
     */
    @Override
    protected void populateClosed(Rental rental) {
        closedCheckBox.setSelected(rental.isClosed());
    }

    protected void changeRentalStatus() {
        boolean closed = closedCheckBox.isSelected();
        Rental rental = getValue();

        if (closed && rental.isOpen()) {
            rental.close();
        } else if (!closed && rental.isClosed()) {
            try {
                rental.open();
            } catch (RentalException ex) {
                App.showDatabaseErrorAlert(ex);
                populateClosed(rental);
            }
        }

        try {
            database.updateRental(rental);
        } catch (Exception ex) {
            App.showDatabaseErrorAlert(ex);
        }
    }
}
