//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MockDatabase.java
//  Mock application database.
//  Visual Studio Code

public class MockDatabase implements Database
{
    LinkedList<Customer> customers = new LinkedList<Customer>(
        new Customer(1, "George Washington", "555-555-0001"),
        new Customer(2, "John Adams", "555-555-0002"),
        new Customer(3, "Thomas Jefferson", "555-555-0003"),
        new Customer(4, "James Madison", "555-555-0003")
    );

    LinkedList<Employee> employees = new LinkedList<Employee>(
        new Employee(
            1,
            "Olivia Smith",
            "President & CEO",
            UserLevel.EXECUTIVE),
        new Employee(
            2,
            "Liam Johnson",
            "Regional Director of Operations",
            UserLevel.EXECUTIVE),
        new Employee(
            3,
            "Emma Williams",
            "Assistant Vice President of Risk Management & Legal Affairs",
            UserLevel.EXECUTIVE)
    );

    LinkedList<Rental> rentals = new LinkedList<Rental>();

    LinkedList<Vehicle> vehicles = new LinkedList<Vehicle>(
        new Vehicle(
            1,
            "Acura",
            "RLX",
            "VIN0001",
            Tier.PREMIUM,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            2,
            "Toyota",
            "Corolla",
            "VIN0002",
            Tier.ECONOMY,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            3,
            "Ford",
            "Focus",
            "VIN0003",
            Tier.ECONOMY,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            4,
            "Peugeot",
            "205 T16",
            "VIN0004",
            Tier.PREMIUM,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            5,
            "Citroen",
            "C4",
            "VIN0005",
            Tier.STANDARD,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            6,
            "Lancia",
            "Stratos",
            "VIN0006",
            Tier.LUXURY,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            7,
            "Opel",
            "Ascona",
            "VIN0007",
            Tier.STANDARD,
            VehicleStatus.AVAILABLE),
        new Vehicle(
            8,
            "Toyota",
            "4Runner",
            "VIN0008",
            Tier.FULL_SIZE,
            VehicleStatus.AVAILABLE)
        );

    LinkedList<DatabaseListener> listeners = new LinkedList<DatabaseListener>();

    //  ------------------------------------------------------------------------
    public void addCustomer(Customer customer) throws Exception {
        customers.append(customer);

        onRecordAdded();
    }

    //  ------------------------------------------------------------------------
    public void addEmployee(Employee employee) throws Exception {
        employees.append(employee);

        onRecordAdded();
    }

    //  ------------------------------------------------------------------------
    public void addListener(DatabaseListener listener) {
        if (listener != null) {
            listeners.append(listener);
        }
    }

    //  ------------------------------------------------------------------------
    public void addRental(Rental rental) throws Exception {
        if (rental.getStatus() != RentalStatus.OPEN) {
            throw new DatabaseException("Rental status is invalid.");
        }

        if (rental.getVehicle().getStatus() != VehicleStatus.AVAILABLE) {
            throw new DatabaseException("Vehicle status is invalid.");
        }

        rental.getVehicle().setStatus(VehicleStatus.CHECKED_OUT);

        rentals.append(rental);

        onRecordAdded();
    }

    //  ------------------------------------------------------------------------
    public void addVehicle(Vehicle vehicle) throws Exception {
        vehicles.append(vehicle);

        onRecordAdded();
    }

    //  ------------------------------------------------------------------------
    public Query<Customer> queryCustomers() throws Exception {
        return new Query<Customer>(customers);
    }

    //  ------------------------------------------------------------------------
    public Query<Employee> queryEmployees() throws Exception {
        return new Query<Employee>(employees);
    }

    //  ------------------------------------------------------------------------
    public Query<Rental> queryRentals() throws Exception {
        return new Query<Rental>(rentals);
    }

    //  ------------------------------------------------------------------------
    public Query<Vehicle> queryVehicles() throws Exception {
        return new Query<Vehicle>(vehicles);
    }

    //  ------------------------------------------------------------------------
    private void onRecordAdded() {
        for (DatabaseListener listener : listeners) {
            listener.onRecordAdded();
        }
    }
}
