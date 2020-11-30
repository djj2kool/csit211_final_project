//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialogController.java
//  Controller for new rental dialog.
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
import javafx.stage.Stage;

public class AddRentalDialogController extends DialogController<Rental> implements Initializable
{
    @FXML private TextField customerField;
    @FXML private TextField mileageField;
    @FXML private TextField priceField;
    @FXML private TextField vehicleField;

    Stage stage;
    Customer customer = null;
    Rental rental = null;
    Vehicle vehicle = null;

    //  ------------------------------------------------------------------------
    private void clear() {
        rental = null;
        mileageField.setText("");
        priceField.setText("");
        setCustomer(null);
        setVehicle(null);
    }

    //  ------------------------------------------------------------------------
    public void close() {
        clear();
        stage.close();
    }

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
                updateEstimatedPrice();
            }
        });
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        try {
            if (customer != null && vehicle != null) {
                rental = new Rental(customer, vehicle);
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
    @FXML
    private void onCancelButton(ActionEvent event) {
        close();
    }

    //  ------------------------------------------------------------------------
    @FXML
    void onMileageFieldChanged(ActionEvent event) {
        updateEstimatedPrice();
    }

    //  ------------------------------------------------------------------------
    void setCustomer(Customer customer) {
        String description;

        this.customer = customer;
        description = customer != null ? customer.getName() : "";
        this.customerField.setText(description);
    }

    //  ------------------------------------------------------------------------
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //  ------------------------------------------------------------------------
    void setVehicle(Vehicle vehicle) {
        String description;

        if (vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE) {
            description = String.format(
                "%s %s",
                vehicle.getMake(),
                vehicle.getModel());
            this.vehicle = vehicle;
            this.vehicleField.setText(description);
        } else {
            this.vehicleField.setText("");
        }

        updateEstimatedPrice();
    }

    //  ------------------------------------------------------------------------
    private void updateEstimatedPrice() {
        double mileage;
        double price;
        Locale currentLocale;
        NumberFormat numberFormat;

        try {
            if (vehicle != null) {
                mileage = Double.parseDouble(this.mileageField.getText());
                price = this.vehicle.calculatePrice(mileage);

                currentLocale = Locale.getDefault();
                numberFormat = NumberFormat.getCurrencyInstance(currentLocale);

                priceField.setText(numberFormat.format(price));
            }
        } catch (NumberFormatException ex) {
            priceField.setText("");
        }
    }
}
