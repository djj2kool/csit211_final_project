//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  SqlLiteDatabase.java
//  SQLLite application database.
//  Visual Studio Code

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.Statement;

public class SqlLiteDatabase implements Database
{
    LinkedList<DatabaseListener> listeners = new LinkedList<DatabaseListener>();

    //  ------------------------------------------------------------------------
    public void addCustomer(Customer customer) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            statement = connection.prepareStatement("INSERT INTO `customers`(name, phone) VALUES (?, ?)");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    //  ------------------------------------------------------------------------
    public void addEmployee(Employee employee) throws Exception {
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
    public void addListener(DatabaseListener listener) {
        if (listener != null) {
            listeners.append(listener);
        }
    }

    //  ------------------------------------------------------------------------
    public void addRental(Rental rental) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = createConnection();
            statement = connection.prepareStatement("INSERT INTO `rentals`(customerId, vehicleId, status) VALUES (?, ?, ?)");
            statement.setInt(1, rental.getCustomer().getId());
            statement.setInt(2, rental.getVehicle().getId());
            statement.setInt(3, rental.getStatus().toInt());
            statement.executeUpdate();
        }
        finally {
            connection.close();
        }
    }

    //  ------------------------------------------------------------------------
    private Connection createConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:app.db");
    }

    //  ------------------------------------------------------------------------
    private Customer createCustomer(ResultSet rs) throws Exception {
        int id = rs.getInt("customerID");
        String name = rs.getString("customerName");
        String phone = rs.getString("customerPhone");
        return new Customer(id, name, phone);
    }

    //  ------------------------------------------------------------------------
    private Employee createEmployee(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String title = rs.getString("title");
        UserLevel level = UserLevel.fromInt(rs.getInt("level"));
        return new Employee(id, name, title, level);
    }

    //  ------------------------------------------------------------------------
    private Rental createRental(ResultSet rs) throws Exception {
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
    private Statement createStatement(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        return statement;
    }

    //  ------------------------------------------------------------------------
    private Vehicle createVehicle(ResultSet rs) throws Exception {
        int id = rs.getInt("vehicleId");
        String make = rs.getString("vehicleMake");
        String model = rs.getString("vehicleModel");
        String vin = rs.getString("vehicleVin");
        Tier tier = Tier.fromInt(rs.getInt("vehicleTier"));
        VehicleStatus status = VehicleStatus.fromInt(rs.getInt("vehicleStatus"));
        return new Vehicle(id, make, model, vin, tier, status);
    }

    //  ------------------------------------------------------------------------
    public Query<Customer> queryCustomers() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        LinkedList<Customer> customers = new LinkedList<Customer>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT " +
                "id AS customerID, " +
                "name AS customerName, " +
                "phone AS customerPhone " +
                "FROM Customers");

            while(rs.next()) {
                customers.append(createCustomer(rs));
            }
        }
        finally {
            connection.close();
        }

        return new Query<Customer>(customers);
    }

    //  ------------------------------------------------------------------------
    public Query<Employee> queryEmployees() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        LinkedList<Employee> employees = new LinkedList<Employee>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery("SELECT * FROM Employees");

            while(rs.next()) {
                employees.append(createEmployee(rs));
            }
        }
        finally {
            connection.close();
        }

        return new Query<Employee>(employees);
    }

    //  ------------------------------------------------------------------------
    public Query<Rental> queryRentals() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        LinkedList<Rental> rentals = new LinkedList<Rental>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT r.*, " +
                //  Customer columns
                "c.id AS customerId, " +
                "c.name AS customerName, " +
                "c.phone AS customerPhone, " +
                //  Vehicle columns
                "v.id AS vehicleID, " +
                "v.make AS vehicleMake, " +
                "v.model AS vehicleModel, " +
                "vin AS vehicleVin, " +
                "v.tier AS vehicleTier, " +
                "v.status AS vehicleStatus " +
                //  Joins
                "FROM Rentals AS r " +
                "JOIN Customers AS c " +
                "ON r.customerId = c.id " +
                "JOIN Vehicles AS v " +
                "ON r.vehicleId = v.id");

            while(rs.next()) {
                rentals.append(createRental(rs));
            }
        }
        finally {
            connection.close();
        }

        return new Query<Rental>(rentals);
    }

    //  ------------------------------------------------------------------------
    public Query<Vehicle> queryVehicles() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        LinkedList<Vehicle> vehicles = new LinkedList<Vehicle>();

        try {
            connection = createConnection();
            statement = createStatement(connection);
            rs = statement.executeQuery(
                "SELECT " +
                "id AS vehicleID, " +
                "make AS vehicleMake, " +
                "model AS vehicleModel, " +
                "vin AS vehicleVin, " +
                "tier AS vehicleTier, " +
                "status AS vehicleStatus " +
                "FROM Vehicles"
            );

            while(rs.next()) {
                vehicles.append(createVehicle(rs));
            }
        }
        finally {
            connection.close();
        }

        return new Query<Vehicle>(vehicles);
    }
}
