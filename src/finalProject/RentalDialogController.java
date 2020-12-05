//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalDialogController.java
//  Controller for rental dialogs.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class RentalDialogController extends DialogController<Rental>
{
    @FXML private TextField customerField;
    @FXML private TextField mileageField;
    @FXML private TextField priceField;
    @FXML private TextField vehicleField;

    private Rental rental = new Rental();

    /**
     * Clears dialog fields.
     */
    protected void clear() {
        this.rental = new Rental();
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

    protected void createRental() {
        int mileage;

        try {
            mileage = getMileage();

            if (rental.getCustomer() != null &&
                rental.getVehicle() != null &&
                mileage >= 0
            ) {
                rental.setMileage(mileage);
                database.addRental(rental);

                close();
            }
        } catch (Exception ex) {
        }
    }

    protected void editRental() {
        int mileage;

        try {
            mileage = getMileage();

            if (rental != null && mileage >= 0) {
                rental.setMileage(mileage);

                database.updateRental(rental);

                close();
            }
        } catch (Exception ex) {
        }
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
     * Gets the rental instance being edited.
     * @return
     */
    public Rental getValue() {
        return rental;
    }

    /**
     * Gets the current vehicle.
     * @return
     */
    protected Vehicle getVehicle() {
        return rental != null ? rental.getVehicle() : null;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        customerField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        vehicleField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        mileageField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateEstimatedPrice(getVehicle());
            onFieldChanged();
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
     * Populates the closed checkbox.
     * @param customer
     */
    protected void populateClosed(Rental rental) {}


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
        populateClosed(rental);
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
     * Sets the rental customer.
     * @param customer
     */
    public void setCustomer(Customer customer) {
        rental.setCustomer(customer);
        populateCustomer(customer);
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
     * Sets the rental vehicle.
     */
    public void setVehicle(Vehicle vehicle) {
        if (vehicle != null &&
            vehicle.getStatus() == VehicleStatus.AVAILABLE
        ) {
            rental.setVehicle(vehicle);
            populateVehicle(vehicle);
        }
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

    /**
     * Checks if all fields required to create a new database object are valid.
     * @return true if fields are all valid, false if fields are invalid
     */
    @Override
    protected boolean validateFields() {
        return (
            rental != null &&
            rental.getCustomer() != null &&
            rental.getVehicle() != null &&
            getMileage() >= 0
        );
    }
}
