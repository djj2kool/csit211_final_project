//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditCustomerDialog.java
//  Dialog for editing a customer.
//  Eclipse, Visual Studio Code

package finalProject;

public class EditCustomerDialog extends Dialog<Customer>
{
    /**
     * Constructs an EditCustomerDialog instance.
     * @param database the database available to the dialog controller
     */
    public EditCustomerDialog(Database database, Customer customer)
    throws Exception {
        super("Edit Customer", "EditCustomerDialog.fxml", database, true);
        setValue(customer);
    }
}
