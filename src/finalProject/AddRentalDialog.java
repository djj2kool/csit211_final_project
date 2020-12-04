//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialog.java
//  Dialog for new rental input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddRentalDialog extends AddDialog<Rental>
{
    /**
     * Constructs an AddRentalDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddRentalDialog(Database database) {
        super("Add New Rental", "AddRentalDialog.fxml", database, false);
    }

    /**
     * Sets the customer for the new rental.
     * @param customer
     */
    public void setCustomer(Customer customer) {
        ((AddRentalDialogController)controller).setCustomer(customer);
    }

    /**
     * Sets the vehicle for the new rental.
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        ((AddRentalDialogController)controller).setVehicle(vehicle);
    }
}
