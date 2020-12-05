//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialogController.java
//  Controller for new customer dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCustomerDialogController extends DialogController<Customer>
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

    /**
     * Called after the add button is activated.
     * @param event
     */
    @FXML
    private void onAddButton(ActionEvent event) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        customer = new Customer(name, phone);
        stage.close();
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
