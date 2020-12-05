//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MainController.java
//  Controller for GUI.
//  Eclipse, Visual Studio Code

package finalProject;

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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class MainController implements DatabaseListener, Initializable
{
    @FXML private TabPane tabPane;

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
    private AddRentalDialog addRentalDialog = null;

    //  Application database
    private Database database;

    //  Index of the rentals view tab
    private final int RENTAL_TAB_INDEX = 2;
    private final int DOUBLE_CLICK = 2;

    /**
     * Changes GUI focus to an item in a table view.
     * @param <T>
     * @param item the item to focus on
     * @param tableView the table view containing the item
     * @param tabIndex the index of the parent tab of the table view
     */
    private <T> void focusItem(T item, TableView<T> tableView, int tabIndex) {
        tabPane.getSelectionModel().select(tabIndex);
        tableView.scrollTo(item);
        tableView.getSelectionModel().select(item);
    }

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
                if (event.getClickCount() == DOUBLE_CLICK && (!row.isEmpty())) {
                    Customer customer = row.getItem();
                    if (addRentalDialog.isShowing()) {
                        addRentalDialog.setCustomer(customer);
                    } else {
                        showEditCustomerDialog(customer);
                    }
                }
            });
            return row;
        });

        //  Customer table view mouse click handler
        //  When a customer is double-clicked use it in the add rental dialog
        employeeTableView.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<Employee>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == DOUBLE_CLICK && (!row.isEmpty())) {
                    Employee employee = row.getItem();
                    if (!addRentalDialog.isShowing()) {
                        showEditEmployeeDialog(employee);
                    }
                }
            });
            return row;
        });

        //  Rental table view mouse click handler
        //  When a rental is double-clicked use it in the edit rental dialog
        rentalsTableView.setRowFactory(tv -> {
            TableRow<Rental> row = new TableRow<Rental>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == DOUBLE_CLICK && (!row.isEmpty())) {
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
                if (event.getClickCount() == DOUBLE_CLICK && (!row.isEmpty())) {
                    Vehicle vehicle = row.getItem();
                    if (addRentalDialog.isShowing()) {
                        addRentalDialog.setVehicle(vehicle);
                    } else {
                        showEditVehicleDialog(vehicle);
                    }
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
    public void onRecordAdded(Object record) {
        refreshTableViews();

        //  Focus on new rental
        if (record instanceof Rental) {
            focusItem((Rental)record, rentalsTableView, RENTAL_TAB_INDEX);
        }
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
        Optional<Customer> customer = null;
        Dialog<Customer> dialog = null;

        dialog = new AddCustomerDialog(database);
        dialog.showAndWait();

        customer = dialog.result();
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
        Optional<Employee> employee = null;
        Dialog<Employee> dialog = null;

        dialog = new AddEmployeeDialog(database);
        dialog.showAndWait();

        employee = dialog.result();
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
        Dialog<Vehicle> dialog = null;
        Optional<Vehicle> vehicle = null;

        dialog = new AddVehicleDialog(database);
        dialog.showAndWait();

        vehicle = dialog.result();
        if (vehicle.isPresent()) {
            database.addVehicle(vehicle.get());
            refreshVehicles();
        }
    }

    /**
     * Displays the dialog box used to edit an existing customer record.
     */
    private void showEditCustomerDialog(Customer customer) {
        Dialog<Customer> dialog = new EditCustomerDialog(database, customer);
        dialog.showAndWait();
        refreshCustomers();
    }

    /**
     * Displays the dialog box used to edit an existing employee record.
     */
    private void showEditEmployeeDialog(Employee employee) {
        Dialog<Employee> dialog = new EditEmployeeDialog(database, employee);
        dialog.showAndWait();
        refreshEmployees();
    }

    /**
     * Displays the dialog box used to edit an existing rental record.
     */
    private void showEditRentalDialog(Rental rental) {
        Dialog<Rental> dialog = new EditRentalDialog(database, rental);
        dialog.showAndWait();
        refreshRentals();
    }

    /**
     * Displays the dialog box used to edit an existing vehicle record.
     */
    private void showEditVehicleDialog(Vehicle vehicle) {
        Dialog<Vehicle> dialog = new EditVehicleDialog(database, vehicle);
        dialog.showAndWait();
        refreshVehicles();
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
