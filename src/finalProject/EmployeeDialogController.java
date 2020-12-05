//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EmployeeDialogController.java
//  Controller for employee dialogs.
//  Eclipse, Visual Studio Code

package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmployeeDialogController extends DialogController<Employee>
{
    @FXML private TextField nameField;
    @FXML private TextField titleField;
    @FXML private ComboBox<UserLevel> levelComboBox;

    Employee employee = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getValue() {
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //  Populate user levels combobox
        ObservableList<UserLevel> levels =
            FXCollections.observableArrayList(
                UserLevel.ASSOCIATE,
                UserLevel.EXECUTIVE,
                UserLevel.ADMIN
            );
        levelComboBox.setItems(levels);
        levelComboBox.setValue(levels.get(0));

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });

        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            onFieldChanged();
        });
    }

    /**
     * Creates a new employee based on data from the dialog fields.
     */
    protected void createEmployee() {
        String name = nameField.getText();
        String title = titleField.getText();
        UserLevel level = levelComboBox.getValue();
        employee = new Employee(name, title, level);
        stage.close();
    }

    /**
     * Edits an existing employee based on data from the dialog fields.
     */
    protected void editEmployee() {
        String name = nameField.getText();
        String title = titleField.getText();
        UserLevel level = levelComboBox.getValue();
        employee.setName(name);
        employee.setTitle(title);
        employee.setLevel(level);

        try {
            database.updateEmployee(employee);
            stage.close();
        } catch (Exception ex) {
            App.showDatabaseErrorAlert(ex);
        }
    }

    /**
     * Called after the cancel button is activated
     * @param event
     */
    @FXML
    private void onCancelButton(ActionEvent event) {
        stage.close();
    }

    /**
     * Populates employee related dialog fields.
     * @param employee
     */
    protected void populateEmployee(Employee employee) {
        if (employee != null) {
            nameField.setText(employee.getName());
            titleField.setText(employee.getTitle());
            levelComboBox.setValue(employee.getLevel());
        } else {
            nameField.clear();
            titleField.clear();
            levelComboBox.setValue(levelComboBox.getItems().get(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Employee employee) {
        this.employee = employee;
        populateEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validateFields() {
        return (
            !nameField.getText().isBlank() &&
            !titleField.getText().isBlank()
        );
    }
}
