//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  FinalProject.java
//  Application entry.
//  Visual Studio Code

import java.util.List;

public class FinalProject
{
    //  ------------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            Database db = new Database();
            List<Employee> employees = db.queryEmployees();
            List<Vehicle> vehicles = db.queryVehicles();

            for (Employee employee : employees) {
                System.out.format(
                    "%d: %s - %s\n",
                    employee.getId(),
                    employee.getName(),
                    employee.getTitle()
                );
            }

            for (Vehicle vehicle : vehicles) {
                System.out.format(
                    "%d: %s %s - $%.2f per-mile\n",
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.calculatePrice(1)
                );
            }
        }
        catch (Exception ex) {
            System.out.println("Failed to connect to database.");
            System.out.println(ex);
        }
    }
}
