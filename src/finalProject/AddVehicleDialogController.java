//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialogController.java
//  Controller for new vehicle dialog.
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

public class AddVehicleDialogController extends DialogController<Vehicle>
{
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField vinField;
    @FXML private ComboBox<Tier> tierComboBox;

    Vehicle vehicle = null;

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
    public Vehicle getValue() {
        return vehicle;
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        String make = makeField.getText();
        String model = modelField.getText();
        String vin = vinField.getText();
        Tier tier = tierComboBox.getValue();
        vehicle = new Vehicle(make, model, vin, tier);
        stage.close();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
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
