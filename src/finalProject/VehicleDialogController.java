//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  VehicleDialogController.java
//  Controller for vehicle dialogs.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VehicleDialogController extends DialogController<Vehicle>
{
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField vinField;
    @FXML private ComboBox<Tier> tierComboBox;

    Vehicle vehicle = null;

    //  ------------------------------------------------------------------------
    public Vehicle getValue() {
        return vehicle;
    }

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //  Populate tiers combobox
        ObservableList<Tier> tiers =
            FXCollections.observableArrayList(
                Tier.ECONOMY,
                Tier.COMPACT,
                Tier.STANDARD,
                Tier.FULL_SIZE,
                Tier.PREMIUM,
                Tier.LUXURY
            );
        tierComboBox.setItems(tiers);
        tierComboBox.setValue(tiers.get(0));

        makeField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        modelField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        vinField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });
    }

    //  ------------------------------------------------------------------------
    protected void createVehicle() {
        String make = makeField.getText();
        String model = modelField.getText();
        String vin = vinField.getText();
        Tier tier = tierComboBox.getValue();
        vehicle = new Vehicle(make, model, vin, tier);
        stage.close();
    }

    //  ------------------------------------------------------------------------
    protected void editVehicle() {
        String make = makeField.getText();
        String model = modelField.getText();
        String vin = vinField.getText();
        Tier tier = tierComboBox.getValue();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setVin(vin);
        vehicle.setTier(tier);

        try {
            database.updateVehicle(vehicle);
            stage.close();
        } catch (Exception ex) {
            App.showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }

    //  ------------------------------------------------------------------------
    protected void populateVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            makeField.setText(vehicle.getMake());
            modelField.setText(vehicle.getModel());
            vinField.setText(vehicle.getVin());
            tierComboBox.setValue(vehicle.getTier());
        } else {
            makeField.clear();
            modelField.clear();
            vinField.clear();
            tierComboBox.setValue(tierComboBox.getItems().get(0));
        }
    }

    /**
     * Sets the value this dialog should return.
     * @param value
     */
    @Override
    public void setValue(Vehicle vehicle) {
        this.vehicle = vehicle;
        populateVehicle(vehicle);
    }

    //  ------------------------------------------------------------------------
    @Override
    protected boolean validateFields() {
        return (
            !makeField.getText().isBlank() &&
            !modelField.getText().isBlank() &&
            !vinField.getText().isBlank()
        );
    }
}
