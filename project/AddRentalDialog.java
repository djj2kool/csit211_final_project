//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddRentalDialog.java
//  Dialog for new rental input.
//  Visual Studio Code

public class AddRentalDialog extends AddDialog<Rental>
{
    //  ------------------------------------------------------------------------
    public AddRentalDialog() {
        super("Add New Rental", "AddRentalDialog.fxml", false);
    }

    //  ------------------------------------------------------------------------
    public void setCustomer(Customer customer) {
        ((AddRentalDialogController)controller).setCustomer(customer);
    }

    //  ------------------------------------------------------------------------
    public void setVehicle(Vehicle vehicle) {
        ((AddRentalDialogController)controller).setVehicle(vehicle);
    }
}
