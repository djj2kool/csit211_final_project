//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  VehiclesController.java
//  Controller for vehicle TableView.
//  Visual Studio Code

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.scene.control.TableView;
import java.util.ResourceBundle;

public class VehiclesController implements Initializable
{
    @FXML private TableView<Vehicle> vehicleTableView;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            vehicleTableView.getItems().setAll(Database.queryVehicles());
        }
        catch (Exception ex) {
        }
    }
}
