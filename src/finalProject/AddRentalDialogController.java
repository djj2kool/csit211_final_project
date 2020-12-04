//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialogController.java
//  Controller for new rental dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRentalDialogController extends RentalDialogController
{
    @FXML private TextField customerField;
    @FXML private TextField vehicleField;
    @FXML private TextField mileageField;

    private Customer customer = null;
    private Rental rental = null;
    private Vehicle vehicle = null;

    //  ------------------------------------------------------------------------
    @Override
    protected void clear() {
        rental = null;
        customer = null;
        vehicle = null;
        super.clear();
    }

    //  ------------------------------------------------------------------------
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
            onFieldChanged();
        });
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        int mileage;

        try {
            mileage = getMileage();

            if (customer != null && vehicle != null && mileage >= 0) {
                rental = new Rental(mileage, customer, vehicle);
                database.addRental(rental);

                close();
            }
        } catch (Exception ex) {
        }
    }

    //  ------------------------------------------------------------------------
    public Rental getValue() {
        return rental;
    }

    //  ------------------------------------------------------------------------
    protected Vehicle getVehicle() {
        return vehicle;
    }

    //  ------------------------------------------------------------------------
    public void setCustomer(Customer customer) {
        this.customer = customer;
        populateCustomer(customer);
    }

    //  ------------------------------------------------------------------------
    public void setVehicle(Vehicle vehicle) {
        if (vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE) {
            this.vehicle = vehicle;
            populateVehicle(vehicle);
        }
    }

    /**
     * Checks if all fields required to create a new database object are valid.
     * @return true if fields are all valid, false if fields are invalid
     */
    @Override
    protected boolean validateFields() {
        return customer != null && vehicle != null && getMileage() >= 0;
    }
}
