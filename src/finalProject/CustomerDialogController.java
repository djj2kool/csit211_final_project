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
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;

    Customer customer = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer getValue() {
        return customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });
    }

    /**
     * Creates a new customer using dialog field data.
     */
    protected void createCustomer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        customer = new Customer(firstName, lastName, phone);
        stage.close();
    }

    /**
     * Edits an existing customer using dialog field data.
     */
    protected void editCustomer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);

        try {
            database.updateCustomer(customer);
            stage.close();
        } catch (Exception ex) {
            App.showErrorAlert(ex);
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
            firstNameField.setText(customer.getFirstName());
            lastNameField.setText(customer.getLastName());
            phoneField.setText(customer.getPhone());

        } else {
            firstNameField.clear();
            lastNameField.clear();
            phoneField.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Customer customer) {
        this.customer = customer;
        populateCustomer(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validateFields() {
        return (
            !firstNameField.getText().isBlank() &&
            !lastNameField.getText().isBlank() &&
            !phoneField.getText().isBlank()
        );
    }
}
