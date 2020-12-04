//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalDialogController.java
//  Controller for rental dialogs.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class RentalDialogController extends DialogController<Rental>
{
    @FXML private TextField customerField;
    @FXML private TextField mileageField;
    @FXML private TextField priceField;
    @FXML private TextField vehicleField;

    /**
     * Clears dialog fields.
     */
    protected void clear() {
        mileageField.setText("");
        priceField.setText("");
        populateMileage(0);
        populateCustomer(null);
        populateVehicle(null);
    }

    /**
     * Closes the dialog.
     */
    public void close() {
        clear();
        stage.close();
    }

    /**
     * Returns the user-entered mileage.
     * @return mileage or zero if the user-entered value could not be parsed
     */
    protected int getMileage() {
        int mileage = 0;

        try {
            mileage = Integer.parseInt(mileageField.getText());
        } catch (Exception ex) {
        }

        return Math.max(0, mileage);
    }

    /**
     * Gets the current vehicle.
     * This could be the selected vehicle in the add rental dialog, or the
     * nested vehicle member of the rental being edited (in the edit dialog).
     * @return
     */
    protected abstract Vehicle getVehicle();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //  Estimated mileage text field changed listener
        mileageField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                ObservableValue<? extends String> observable,
                String oldValue,
                String newValue
            ) {
                updateEstimatedPrice(getVehicle());
            }
        });

        populateMileage(0);
    }

    /**
     * Called when the cancel button is activated.
     * @param event
     */
    @FXML
    private void onCancelButton(ActionEvent event) {
        close();
    }

    /**
     * Called after dialog is closed using window close button.
     */
    @Override
    protected void onCloseRequest() {
        //  Clear the dialog if the closed button was used to dismiss it
        //  so the old contents are not seen the next time the dialog is shown.
        clear();
    }

    /**
     * Called after the user changes the mileage field.
     * Updates the estimated price.
     * @param event
     */
    @FXML
    private void onMileageFieldChanged(ActionEvent event) {
        updateEstimatedPrice(getVehicle());
    }

    /**
     * Populates the customer related fields.
     * @param customer
     */
    protected void populateCustomer(Customer customer) {
        String description;
        description = customer != null ? customer.getName() : "";
        customerField.setText(description);
    }

    /**
     * Populates the mileage field.
     * @param mileage
     */
    protected void populateMileage(int mileage) {
        mileageField.setText(Integer.toString(mileage));
    }

    /**
     * Populates fields with values from a rental record.
     * @param rental
     */
    protected void populateRental(Rental rental) {
        int mileage = rental == null ? 0 : rental.getMileage();
        Customer customer = rental == null ? null : rental.getCustomer();
        Vehicle vehicle = rental == null ? null : rental.getVehicle();

        populateCustomer(customer);
        populateMileage(mileage);
        populateVehicle(vehicle);
    }

    /**
     * Populates the vehicle related fields.
     * @param vehicle
     */
    protected void populateVehicle(Vehicle vehicle) {
        String description;

        if (vehicle != null) {
            description = String.format(
                "%s %s",
                vehicle.getMake(),
                vehicle.getModel());
            vehicleField.setText(description);
        } else {
            vehicleField.setText("");
        }

        updateEstimatedPrice(vehicle);
    }

    /**
     * Updates the estimated price based on mileage and vehicle tier.
     * @param vehicle
     */
    private void updateEstimatedPrice(Vehicle vehicle) {
        int mileage;
        double price;
        Locale currentLocale;
        NumberFormat numberFormat;

        try {
            if (vehicle != null) {
                mileage = Integer.parseInt(mileageField.getText());
                price = vehicle.calculatePrice(mileage);

                currentLocale = Locale.getDefault();
                numberFormat = NumberFormat.getCurrencyInstance(currentLocale);

                priceField.setText(numberFormat.format(price));
            } else {
                priceField.setText("");
            }
        } catch (NumberFormatException ex) {
            priceField.setText("");
        }
    }
}
