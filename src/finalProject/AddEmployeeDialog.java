//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  AddEmployeeDialog.java
//  Dialog for new employee input.
//  Eclipse, Visual Studio Code

package finalProject;

public class AddEmployeeDialog extends Dialog<Employee>
{
    /**
     * Constructs an AddEmployeeDialog instance.
     * @param database the database available to the dialog controller
     */
    public AddEmployeeDialog(Database database) throws Exception {
        super("Add New Employee", "AddEmployeeDialog.fxml", database, true);
    }
}
