//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Database.java
//  Application database.
//  Visual Studio Code

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    //  ------------------------------------------------------------------------
    public static void addCustomer(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            statement = connection.prepareStatement("INSERT INTO `customers`(name) VALUES (?)");
            statement.setString(1, customer.getName());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    //  ------------------------------------------------------------------------
    public static void addEmployee(Employee employee) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            statement = connection.prepareStatement("INSERT INTO `employees`(name, title, level) VALUES (?, ?, ?)");
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getTitle());
            statement.setInt(3, employee.getLevel().toInt());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    //  ------------------------------------------------------------------------
    private static Connection createConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:app.db");
    }

    //  ------------------------------------------------------------------------
    private static Customer createCustomer(ResultSet rs) throws Exception {
        int id = rs.getInt("customerID");
        String name = rs.getString("customerName");
        return new Customer(id, name);
    }

    //  ------------------------------------------------------------------------
    private static Employee createEmployee(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String title = rs.getString("title");
        UserLevel level = UserLevel.fromInt(rs.getInt("level"));
        return new Employee(id, name, title, level);
    }

    //  ------------------------------------------------------------------------
    private static Rental createRental(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        RentalStatus status = RentalStatus.fromInt(rs.getInt("status"));
        Customer customer = createCustomer(rs);
        Vehicle vehicle = createVehicle(rs);
        return new Rental(
            id,
            status,
            customer,
            vehicle);
    }

    //  ------------------------------------------------------------------------
    private static Statement createStatement(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        return statement;
    }

    //  ------------------------------------------------------------------------
    private static Vehicle createVehicle(ResultSet rs) throws Exception {
        int id = rs.getInt("vehicleId");
        String make = rs.getString("vehicleMake");
        String model = rs.getString("vehicleModel");
        Tier tier = Tier.intToTier(rs.getInt("vehicleTier"));
        Status status = Status.intToStatus(rs.getInt("vehicleStatus"));
        return new Vehicle(id, make, model, tier, status);
    }

    //  ------------------------------------------------------------------------
    public static List<Customer> queryCustomers() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<Customer>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT " +
                "id AS customerID, " +
                "name AS customerName " +
                "FROM Customers");

            while(rs.next()) {
                customers.add(createCustomer(rs));
            }
        }
        finally {
            connection.close();
        }

        return customers;
    }

    //  ------------------------------------------------------------------------
    public static List<Employee> queryEmployees() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery("SELECT * FROM Employees");

            while(rs.next()) {
                employees.add(createEmployee(rs));
            }
        }
        finally {
            connection.close();
        }

        return employees;
    }

    //  ------------------------------------------------------------------------
    public static List<Rental> queryRentals() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Rental> rentals = new ArrayList<Rental>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT r.*, " +
                //  Customer columns
                "c.id AS customerId, " +
                "c.name AS customerName, " +
                //  Vehicle columns
                "v.id AS vehicleID, " +
                "v.make AS vehicleMake, " +
                "v.model AS vehicleModel, " +
                "v.tier AS vehicleTier, " +
                "v.status AS vehicleStatus " +
                //  Joins
                "FROM Rentals AS r " +
                "JOIN Customers AS c " +
                "ON r.customerId = c.id " +
                "JOIN Vehicles AS v " +
                "ON r.vehicleId = v.id");

            while(rs.next()) {
                rentals.add(createRental(rs));
            }
        }
        finally {
            connection.close();
        }

        return rentals;
    }

    //  ------------------------------------------------------------------------
    public static List<Vehicle> queryVehicles() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT " +
                "id AS vehicleID, " +
                "make AS vehicleMake, " +
                "model AS vehicleModel, " +
                "tier AS vehicleTier, " +
                "status AS vehicleStatus " +
                "FROM Vehicles"
            );

            while(rs.next()) {
                vehicles.add(createVehicle(rs));
            }
        }
        finally {
            connection.close();
        }

        return vehicles;
    }
}
