//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddEmployeeDialogController.java
//  Controller for new employee dialog.
//  Visual Studio Code

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeDialogController extends DialogController<Employee> implements Initializable
{
    @FXML private TextField nameField;
    @FXML private TextField titleField;
    @FXML private ComboBox<UserLevel> levelComboBox;

    Stage stage;
    Employee employee = null;

    //  ------------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<UserLevel> levels =
        FXCollections.observableArrayList(
            UserLevel.ASSOCIATE,
            UserLevel.EXECUTIVE,
            UserLevel.ADMIN
        );
        levelComboBox.setItems(levels);
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
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
