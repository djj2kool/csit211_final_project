//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Visual Studio Code

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable
{
    @FXML private TableView<Customer> customerTableView;
    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableView<Vehicle> vehicleTableView;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerTableView.getItems().setAll(Database.queryCustomers());
            employeeTableView.getItems().setAll(Database.queryEmployees());
            vehicleTableView.getItems().setAll(Database.queryVehicles());
        }
        catch (Exception ex) {
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddCustomerDialog(ActionEvent event) throws Exception {
        FXMLLoader loader = null;
        Parent root       = null;
        Scene scene       = null;
        Stage dialogStage = null;
        AddCustomerDialogController controller = null;

        loader = new FXMLLoader(getClass().getResource("AddCustomerDialog.fxml"));
        root = loader.load();
        scene = new Scene(root);

        dialogStage = new Stage();
        dialogStage.setTitle("Add New Customer");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.DECORATED);
        dialogStage.setScene(scene);

        controller = loader.getController();
        controller.setStage(dialogStage);

        dialogStage.showAndWait();
    }
}
