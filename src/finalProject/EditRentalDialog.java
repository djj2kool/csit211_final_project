//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialog.java
//  Dialog for rental edit.
//  Eclipse, Visual Studio Code

package finalProject;

public class EditRentalDialog extends RentalDialog
{
    /**
     * Constructs an EditRentalDialog.
     * @param database
     * @param rental
     */
    public EditRentalDialog(Database database, Rental rental) throws Exception {
        super("Edit Rental", "EditRentalDialog.fxml", database);
        setValue(rental);
    }
}
