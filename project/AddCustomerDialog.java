//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddCustomerDialog.java
//  Dialog for new customer input.
//  Visual Studio Code

public class AddCustomerDialog extends AddDialog<Customer>
{
    //  ------------------------------------------------------------------------
    public AddCustomerDialog(Database database) {
        super("Add New Customer", "AddCustomerDialog.fxml", database, true);
    }
}
