//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialogController.java
//  Controller for new vehicle dialog.
//  Visual Studio Code

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddVehicleDialogController extends DialogController<Vehicle> implements Initializable
{
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField vinField;
    @FXML private ComboBox<Tier> tierComboBox;

    Vehicle vehicle = null;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
