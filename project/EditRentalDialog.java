//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialog.java
//  Dialog for rental edit.
//  Visual Studio Code

public class EditRentalDialog extends EditDialog<Rental>
{
    //  ------------------------------------------------------------------------
    public EditRentalDialog(Database database, Rental rental) {
        super("Edit Rental", "EditRentalDialog.fxml", database, rental);
    }
}
