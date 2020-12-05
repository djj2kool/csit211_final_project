//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialog.java
//  Dialog for new vehicle input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddVehicleDialog extends Dialog<Vehicle>
{
    /**
     * Constructs an AddVehicleDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddVehicleDialog(Database database) throws Exception {
        super("Add New Vehicle", "AddVehicleDialog.fxml", database, true);
    }
}
