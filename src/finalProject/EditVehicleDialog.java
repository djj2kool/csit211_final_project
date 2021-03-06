//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditVehicleDialog.java
//  Dialog for vehicle edit.
//  Eclipse, Visual Studio Code

package finalProject;

public class EditVehicleDialog extends Dialog<Vehicle>
{
    /**
     * Constructs an EditVehicleDialog.
     * @param database
     * @param vehicle
     */
    public EditVehicleDialog(Database database, Vehicle vehicle)
    throws Exception {
        super("Edit Vehicle", "EditVehicleDialog.fxml", database, true);
        setValue(vehicle);
    }
}
