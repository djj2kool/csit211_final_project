//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialog.java
//  Dialog for new vehicle input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddVehicleDialog extends AddDialog<Vehicle>
{
    /**
     * Constructs an AddVehicleDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddVehicleDialog(Database database) {
        super("Add New Vehicle", "AddVehicleDialog.fxml", database, true);
    }
}
