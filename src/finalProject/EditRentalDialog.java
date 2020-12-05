//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialog.java
//  Dialog for rental edit.
//  Eclipse, Visual Studio Code

package finalProject;

public class EditRentalDialog extends Dialog<Rental>
{
    //  ------------------------------------------------------------------------
    public EditRentalDialog(Database database, Rental rental) {
        super("Edit Rental", "EditRentalDialog.fxml", database, true);
        setValue(rental);
    }
}
