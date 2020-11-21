//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Visual Studio Code

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.scene.control.TableView;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableView<Vehicle> vehicleTableView;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            employeeTableView.getItems().setAll(Database.queryEmployees());
            vehicleTableView.getItems().setAll(Database.queryVehicles());
        }
        catch (Exception ex) {
        }
    }
}
