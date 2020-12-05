//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditEmployeeDialog.java
//  Dialog for editing an employee.
//  Eclipse, Visual Studio Code

package finalProject;

public class EditEmployeeDialog extends Dialog<Employee>
{
    /**
     * Constructs an EditEmployeeDialog instance.
     * @param database the database available to the dialog controller
     */
    public EditEmployeeDialog(Database database, Employee employee) {
        super("Edit Employee", "EditEmployeeDialog.fxml", database, true);
        setValue(employee);
    }
}
