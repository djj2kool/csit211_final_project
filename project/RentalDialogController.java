//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalDialogController.java
//  Controller for rental dialogs.
//  Visual Studio Code

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public abstract class RentalDialogController extends DialogController<Rental> implements Initializable
{
    @FXML private TextField customerField;
    @FXML private TextField mileageField;
    @FXML private TextField priceField;
    @FXML private TextField vehicleField;

    //  ------------------------------------------------------------------------
    protected void clear() {
        mileageField.setText("");
        priceField.setText("");
        populateCustomer(null);
        populateVehicle(null);
    }

    //  ------------------------------------------------------------------------
    public void close() {
        clear();
        stage.close();
    }

    //  ------------------------------------------------------------------------
    protected int getMileage() {
        int mileage = 0;

        try {
            mileage = Integer.parseInt(mileageField.getText());
        } catch (Exception ex) {
        }

        return Math.max(0, mileage);
    }

    //  ------------------------------------------------------------------------
    protected abstract Vehicle getVehicle();

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        close();
    }

    //  ------------------------------------------------------------------------
    @Override
    protected void onCloseRequest() {
        //  Clear the dialog if the closed button was used to dismiss it
        //  so the old contents are not seen the next time the dialog is shown.
        clear();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onMileageFieldChanged(ActionEvent event) {
        updateEstimatedPrice(getVehicle());
    }

    //  ------------------------------------------------------------------------
    protected void populateCustomer(Customer customer) {
        String description;
        description = customer != null ? customer.getName() : "";
        customerField.setText(description);
    }

    //  ------------------------------------------------------------------------
    protected void populateMileage(int mileage) {
        mileageField.setText(Integer.toString(mileage));
    }

    //  ------------------------------------------------------------------------
    protected void populateRental(Rental rental) {
        int mileage = rental == null ? 0 : rental.getMileage();
        Customer customer = rental == null ? null : rental.getCustomer();
        Vehicle vehicle = rental == null ? null : rental.getVehicle();

        populateCustomer(customer);
        populateMileage(mileage);
        populateVehicle(vehicle);
    }

    //  ------------------------------------------------------------------------
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

    //  ------------------------------------------------------------------------
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
