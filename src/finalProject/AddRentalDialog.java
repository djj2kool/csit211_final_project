//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialog.java
//  Dialog for new rental input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddRentalDialog extends RentalDialog
{
    /**
     * Constructs an AddRentalDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddRentalDialog(Database database) throws Exception {
        super("Add New Rental", "AddRentalDialog.fxml", database);
    }
}
