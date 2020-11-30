//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddVehicleDialog.java
//  Dialog for new vehicle input.
//  Visual Studio Code

public class AddVehicleDialog extends AddDialog<Vehicle>
{
    //  ------------------------------------------------------------------------
    public AddVehicleDialog(Database database) {
        super("Add New Vehicle", "AddVehicleDialog.fxml", database, true);
    }
}
