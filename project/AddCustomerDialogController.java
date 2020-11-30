//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialogController.java
//  Controller for new customer dialog.
//  Visual Studio Code

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddCustomerDialogController extends DialogController<Customer> implements Initializable
{
    @FXML private TextField nameField;
    @FXML private TextField phoneField;

    Customer customer = null;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        customer = new Customer(name, phone);
        stage.close();
    }

    //  ------------------------------------------------------------------------
    public Customer getValue() {
        return customer;
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }
}
