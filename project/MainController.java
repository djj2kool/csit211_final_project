//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Visual Studio Code

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

public class MainController implements DatabaseListener, Initializable
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

    Database database;

    //  ------------------------------------------------------------------------
    @SuppressWarnings("unchecked")  //  Array of generic types causes warning
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.database = new MockDatabase();
        this.database.addListener(this);

        ObservableList<Filter<Rental>> rentalFilters = null;

        addRentalDialog = new AddRentalDialog(database);

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
        rentalFilters =
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

        refreshTableViews();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onQuit() {
        Platform.exit();
    }

    //  ------------------------------------------------------------------------
    public void onRecordAdded() {
        refreshTableViews();
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
                database.queryCustomers());
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
                database.queryEmployees());
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
            rentals = database.queryRentals();
            filter = rentalFilterComboBox.getValue();
            rentals = rentals.filter(filter);
            populateTableView(rentalsTableView, rentalCountLabel, rentals);
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    private void refreshTableViews() {
        refreshCustomers();
        refreshEmployees();
        refreshRentals();
        refreshVehicles();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void refreshVehicles() {
        try {
            populateTableView(
                vehicleTableView,
                vehicleCountLabel,
                database.queryVehicles());
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddCustomerDialog(ActionEvent event) throws Exception {
        AddDialog<Customer> dialog = new AddCustomerDialog(database);
        Optional<Customer> customer = dialog.showAndWait();

        if (customer.isPresent()) {
            database.addCustomer(customer.get());
            refreshCustomers();
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddEmployeeDialog(ActionEvent event) throws Exception {
        AddDialog<Employee> dialog = new AddEmployeeDialog(database);
        Optional<Employee> employee = dialog.showAndWait();

        if (employee.isPresent()) {
            database.addEmployee(employee.get());
            refreshEmployees();
        }
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddRentalDialog(ActionEvent event) throws Exception {
        addRentalDialog.show();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void showAddVehicleDialog(ActionEvent event) throws Exception {
        AddDialog<Vehicle> dialog = new AddVehicleDialog(database);
        Optional<Vehicle> vehicle = dialog.showAndWait();

        if (vehicle.isPresent()) {
            database.addVehicle(vehicle.get());
            refreshVehicles();
        }
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
