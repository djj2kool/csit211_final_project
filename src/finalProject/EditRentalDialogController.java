//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialogController.java
//  Controller for rental edit dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditRentalDialogController extends RentalDialogController
{
    @FXML private TextField mileageField;

    Rental rental = null;

    /**
     * Clears dialog fields.
     */
    @Override
    protected void clear() {
        super.clear();
        rental = null;
    }

    /**
     * Gets the rental instance being edited.
     * @return
     */
    public Rental getValue() {
        return rental;
    }

    /**
     * Gets the rental vehicle.
     * @return
     */
    @Override
    protected Vehicle getVehicle() {
        return rental == null ? null : rental.getVehicle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        mileageField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });
    }

    /**
     * Called when the submit button is activated.
     * @param event
     */
    @FXML
    private void onSubmitButton(ActionEvent event) {
        int mileage;

        try {
            mileage = getMileage();

            if (rental != null && mileage >= 0) {
                rental.setMileage(mileage);
                rental.setStatus(RentalStatus.CLOSED);

                close();
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Sets the value this dialog should return.
     * @param value
     */
    @Override
    public void setValue(Rental rental) {
        this.rental = rental;
        populateRental(rental);
    }

    /**
     * Checks if all fields required to create a new database object are valid.
     * @return true if fields are all valid, false if fields are invalid
     */
    @Override
    protected boolean validateFields() {
        return rental != null && getMileage() >= 0;
    }
}
