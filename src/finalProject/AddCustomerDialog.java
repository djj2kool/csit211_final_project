//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialog.java
//  Dialog for new customer input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddCustomerDialog extends AddDialog<Customer>
{
    /**
     * Constructs an AddCustomerDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddCustomerDialog(Database database) {
        super("Add New Customer", "AddCustomerDialog.fxml", database, true);
    }
}
