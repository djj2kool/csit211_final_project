//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Visual Studio Code

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

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
        AddDialog<Customer> dialog = new AddCustomerDialog();
        Optional<Customer> customer = dialog.showAndWait();

        if (customer.isPresent()) {
            Database.addCustomer(customer.get());
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddEmployeeDialog(ActionEvent event) throws Exception {
        AddDialog<Employee> dialog = new AddEmployeeDialog();
        Optional<Employee> employee = dialog.showAndWait();

        if (employee.isPresent()) {
            Database.addEmployee(employee.get());
        }
    }
}
