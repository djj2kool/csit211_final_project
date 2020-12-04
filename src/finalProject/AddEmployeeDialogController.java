//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddEmployeeDialogController.java
//  Controller for new employee dialog.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddEmployeeDialogController extends DialogController<Employee> implements Initializable
{
    @FXML private TextField nameField;
    @FXML private TextField titleField;
    @FXML private ComboBox<UserLevel> levelComboBox;

    @FXML private Button addButton;

    Employee employee = null;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Populate user levels combobox
        ObservableList<UserLevel> levels =
            FXCollections.observableArrayList(
                UserLevel.ASSOCIATE,
                UserLevel.EXECUTIVE,
                UserLevel.ADMIN
            );
        levelComboBox.setItems(levels);
        levelComboBox.setValue(levels.get(0));

        //  Add button is disabled while fields are empty
        addButton.setDisable(true);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldsChanged();
        });

        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldsChanged();
        });
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        String name = nameField.getText();
        String title = titleField.getText();
        UserLevel level = levelComboBox.getValue();
        employee = new Employee(name, title, level);
        stage.close();
    }

    //  ------------------------------------------------------------------------
    public Employee getValue() {
        return employee;
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }

    //  ------------------------------------------------------------------------
    private void onFieldsChanged() {
        //  Add button is disabled while fields are empty
        addButton.setDisable(
            nameField.getText().isBlank() ||
            titleField.getText().isBlank()
        );
    }
}
