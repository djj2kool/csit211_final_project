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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class MainController implements DatabaseListener, Initializable
{
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

    //  Modeless dialog used to add new rentals
    AddRentalDialog addRentalDialog = null;

    //  Application database
    Database database;

    /**
     * Initializes the controller.
     */
    @SuppressWarnings("unchecked")  //  Array of generic types causes warning
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.database = new MockDatabase();
        this.database.addListener(this);

        ObservableList<Filter<Rental>> rentalFilters = null;

        addRentalDialog = new AddRentalDialog(database);

        //  Rental table view columns
        //  To access the customer and vehicle properties
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
        //  When a customer is double-clicked use it in the add rental dialog
        customerTableView.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<Customer>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer customer = row.getItem();
                    addRentalDialog.setCustomer(customer);
                }
            });
            return row;
        });

        //  Rental table view mouse click handler
        //  When a rental is double-clicked use it in the edit rental dialog
        rentalsTableView.setRowFactory(tv -> {
            TableRow<Rental> row = new TableRow<Rental>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Rental rental = row.getItem();
                    showEditRentalDialog(rental);
                }
            });
            return row;
        });

        //  Vehicle table view mouse click handler
        //  When a vehicle is double-clicked use it in the add rental dialog
        vehicleTableView.setRowFactory(tv -> {
            TableRow<Vehicle> row = new TableRow<Vehicle>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Vehicle vehicle = row.getItem();
                    addRentalDialog.setVehicle(vehicle);
                }
            });
            return row;
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

    /**
     * Exports a rental report.
     */
    @FXML
    private void onExportRentalsReport() {
        try {
            RentalComparator comparator = new RentalComparator();
            Query<Rental> query = database.queryRentals();
            Rental[] sorted = Sort.mergeSort(Rental.class, query, comparator);

            //  TODO: Export to report
            for (Rental rental : sorted) {
                System.out.println(rental.getId());
            }
        } catch (Exception ex) {
            showDatabaseErrorAlert(ex);
        }
    }

    /**
     * Quits the application.
     */
    @FXML
    private void onQuit() {
        Platform.exit();
    }

    /**
     * Refreshes all table views after a new database record is added.
     */
    public void onRecordAdded() {
        refreshTableViews();
    }

    /**
     * Sets the items of a table view and updates the status bar.
     * @param <T> table view item type
     * @param tableView table view to populate
     * @param countLabel status bar label to update
     * @param query elements to populate table view with
     */
    private <T> void populateTableView(
        TableView<T> tableView,
        Label countLabel,
        Query<T> query
    ) {
        String count = String.format("%d records fetched.", query.size());
        countLabel.setText(count);
        tableView.getItems().setAll(query);
    }

    /**
     * Refreshes the customer table view with records from the database.
     */
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

    /**
     * Refreshes the employee table view with records from the database.
     */
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

    /**
     * Refreshes the rentals table view with records from the database.
     */
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

    /**
     * Refreshes all table views with records from the database.
     */
    private void refreshTableViews() {
        refreshCustomers();
        refreshEmployees();
        refreshRentals();
        refreshVehicles();
    }

    /**
     * Refreshes the vehicles table view with records from the database.
     */
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

    /**
     * Displays the dialog box used to add a new customer record.
     */
    @FXML
    private void showAddCustomerDialog(ActionEvent event) throws Exception {
        AddDialog<Customer> dialog = new AddCustomerDialog(database);
        Optional<Customer> customer = dialog.showAndWait();

        if (customer.isPresent()) {
            database.addCustomer(customer.get());
            refreshCustomers();
        }
    }

    /**
     * Displays the dialog box used to add a new employee record.
     */
    @FXML
    private void showAddEmployeeDialog(ActionEvent event) throws Exception {
        AddDialog<Employee> dialog = new AddEmployeeDialog(database);
        Optional<Employee> employee = dialog.showAndWait();

        if (employee.isPresent()) {
            database.addEmployee(employee.get());
            refreshEmployees();
        }
    }

    /**
     * Displays the dialog box used to add a new rental record.
     */
    @FXML
    private void showAddRentalDialog(ActionEvent event) throws Exception {
        addRentalDialog.show();
    }

    /**
     * Displays the dialog box used to add a new vehicle record.
     */
    @FXML
    private void showAddVehicleDialog(ActionEvent event) throws Exception {
        AddDialog<Vehicle> dialog = new AddVehicleDialog(database);
        Optional<Vehicle> vehicle = dialog.showAndWait();

        if (vehicle.isPresent()) {
            database.addVehicle(vehicle.get());
            refreshVehicles();
        }
    }

    /**
     * Displays the dialog box used to edit an existing rental record.
     */
    private void showEditRentalDialog(Rental rental) {
        EditDialog<Rental> dialog = new EditRentalDialog(database, rental);
        dialog.showAndWait();
        refreshRentals();
    }

    /**
     * Displays an alert box for exceptions thrown during database operations.
     */
    private void showDatabaseErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Connection Error");
        alert.setContentText(ex.getMessage());
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
