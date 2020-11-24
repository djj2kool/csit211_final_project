//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Visual Studio Code

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class MainController implements Initializable
{
    @FXML private Button refreshVehiclesButton;

    @FXML private ComboBox<Filter<Rental>> rentalFilterComboBox;

    @FXML private TableView<Customer> customerTableView;
    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableView<Rental> rentalsTableView;
    @FXML private TableView<Vehicle> vehicleTableView;

    //  Rental table view columns
    @FXML private TableColumn<Rental, String> rentalCustomerTableColumn;
    @FXML private TableColumn<Rental, String> rentalMakeTableColumn;
    @FXML private TableColumn<Rental, String> rentalModelTableColumn;
    @FXML private TableColumn<Rental, Double> rentalPricePerMileTableColumn;
    @FXML private TableColumn<Rental, Tier> rentalTierTableColumn;

    //  Status bar
    @FXML private Label customerCountLabel;
    @FXML private Label employeeCountLabel;
    @FXML private Label rentalCountLabel;
    @FXML private Label vehicleCountLabel;

    AddRentalDialog addRentalDialog = null;

    //  ------------------------------------------------------------------------
    @SuppressWarnings("unchecked")  //  Array of generic types causes warning
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRentalDialog = new AddRentalDialog();

        //  Rental table view columns
        rentalCustomerTableColumn.setCellValueFactory(
            cellData -> Bindings.select(
                cellData.getValue().getCustomer(),
                "name"));
        rentalMakeTableColumn.setCellValueFactory(
            cellData -> Bindings.select(
                cellData.getValue().getVehicle(),
                "make"));
        rentalModelTableColumn.setCellValueFactory(
            cellData -> Bindings.select(
                cellData.getValue().getVehicle(),
                "model"));
        rentalPricePerMileTableColumn.setCellValueFactory(
            cellData -> Bindings.select(
                cellData.getValue().getVehicle(),
                "pricePerMile"));
        rentalTierTableColumn.setCellValueFactory(
            cellData -> Bindings.select(
                cellData.getValue().getVehicle(),
                "tier"));

        //  Customer table view mouse click handler
        customerTableView.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<Customer>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer customer = row.getItem();
                    addRentalDialog.setCustomer(customer);
                }
            });
            return row ;
        });

       //  Vehicle table view mouse click handler
       vehicleTableView.setRowFactory( tv -> {
            TableRow<Vehicle> row = new TableRow<Vehicle>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Vehicle vehicle = row.getItem();
                    addRentalDialog.setVehicle(vehicle);
                }
            });
            return row ;
        });

        //  Initialize rental filter combo box
        ObservableList<Filter<Rental>> rentalFilters =
            FXCollections.observableArrayList(
                new Filter<Rental>(
                    "All Rentals",
                    rental -> true),
                new Filter<Rental>(
                    "Open Rentals",
                    rental -> rental.getStatus() == RentalStatus.OPEN),
                new Filter<Rental>(
                    "Closed Rentals",
                    rental -> rental.getStatus() == RentalStatus.CLOSED)
            );
        rentalFilterComboBox.setItems(rentalFilters);
        rentalFilterComboBox.setValue(rentalFilters.get(0));

        //  Refresh table views
        refreshCustomers();
        refreshEmployees();
        refreshRentals();
        refreshVehicles();
    }

    //  ------------------------------------------------------------------------
    private <T> void populateTableView(
        TableView<T> tableView,
        Label countLabel,
        Query<T> query
    ) {
        String count = String.format("%d records fetched.", query.size());
        countLabel.setText(count);
        tableView.getItems().setAll(query);
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void refreshCustomers() {
        try {
            populateTableView(
                customerTableView,
                customerCountLabel,
                Database.queryCustomers());
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void refreshEmployees() {
        try {
            populateTableView(
                employeeTableView,
                employeeCountLabel,
                Database.queryEmployees());
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void refreshRentals() {
        Query<Rental> rentals;
        Filter<Rental> filter;
        try {
            rentals = Database.queryRentals();
            filter = rentalFilterComboBox.getValue();
            rentals = rentals.filter(filter);
            populateTableView(rentalsTableView, rentalCountLabel, rentals);
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void refreshVehicles() {
        try {
            populateTableView(
                vehicleTableView,
                vehicleCountLabel,
                Database.queryVehicles());
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
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

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddRentalDialog(ActionEvent event) throws Exception {
        addRentalDialog.show();
    }

    //  ------------------------------------------------------------------------
    private void showDatabaseErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Connection Error");
        alert.setContentText(ex.getMessage());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
