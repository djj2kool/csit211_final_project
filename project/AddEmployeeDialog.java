//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddEmployeeDialog.java
//  Dialog for new employee input.
//  Visual Studio Code

public class AddEmployeeDialog extends AddDialog<Employee>
{
    //  ------------------------------------------------------------------------
    public AddEmployeeDialog(Database database) {
        super("Add New Employee", "AddEmployeeDialog.fxml", database, true);
    }
}
