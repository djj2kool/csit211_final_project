//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  CustomerDialogController.java
//  Controller for customer dialogs.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CustomerDialogController extends DialogController<Customer>
{
    @FXML private TextField nameField;
    @FXML private TextField phoneField;

    Customer customer = null;

    /**
     * Gets the customer this dialog should return.
     * @return
     */
    public Customer getValue() {
        return customer;
    }

    /**
     * Initializes the controller.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });
    }

    protected void createCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        customer = new Customer(name, phone);
        stage.close();
    }

    protected void editCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        customer.setName(name);
        customer.setPhone(phone);

        try {
            database.updateCustomer(customer);
            stage.close();
        } catch (Exception ex) {
            App.showDatabaseErrorAlert(ex);
        }
    }

    /**
     * Called after the cancel button is pressed
     * @param event
     */
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }

    /**
     * Populates the customer related fields.
     * @param customer
     */
    protected void populateCustomer(Customer customer) {
        if (customer != null) {
            nameField.setText(customer.getName());
            phoneField.setText(customer.getPhone());

        } else {
            nameField.clear();
            phoneField.clear();
        }
    }

    /**
     * Sets the value this dialog should return.
     * @param value
     */
    @Override
    public void setValue(Customer customer) {
        this.customer = customer;
        populateCustomer(customer);
    }

    /**
     * Called after a field changes.
     */
    @Override
    protected boolean validateFields() {
        return (
            !nameField.getText().isBlank() &&
            !phoneField.getText().isBlank()
        );
    }
}
