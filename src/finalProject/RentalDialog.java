//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalDialog.java
//  Dialog for new rental input.
//  Eclipse, Visual Studio Code

package finalProject;

public class RentalDialog extends Dialog<Rental>
{
    /**
     * Constructs a dialog.
     * @param title the title to display in the dialog titlebar
     * @param fxml FXML
     * @param database
     */
    public RentalDialog(String title, String fxml, Database database) {
        super(title, fxml, database, false);
    }

    /**
     * Sets the customer for the new rental.
     * @param customer
     */
    public void setCustomer(Customer customer) {
        ((RentalDialogController)controller).setCustomer(customer);
    }

    /**
     * Sets the vehicle for the new rental.
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        ((RentalDialogController)controller).setVehicle(vehicle);
    }
}
