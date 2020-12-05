//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MockDatabase.java
//  Mock application database.
//  Eclipse, Visual Studio Code

package finalProject;

public class MockDatabase implements Database
{
    LinkedList<Customer> customers = new LinkedList<Customer>(
        new Customer(1, "Carlos", "Sainz", "555-555-0001"),
        new Customer(2, "Juha", "Kankkunen", "555-555-0002"),
        new Customer(3, "Walter", "Rohrl", "555-555-0003"),
        new Customer(4, "Colin", "McRae", "555-555-0004"),
        new Customer(5, "Sébastien", "Loeb", "555-555-0005"),
        new Customer(6, "Henri", "Toivonen", "555-555-0006"),
        new Customer(7, "Markku", "Alén", "555-555-0007"),
        new Customer(8, "Michèle", "Mouton", "555-555-0008"),
        new Customer(9, "Tommi", "Mäkinen", "555-555-0009")
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCustomer(Customer customer) throws Exception {
        customers.append(customer);

        onRecordAdded(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEmployee(Employee employee) throws Exception {
        employees.append(employee);

        onRecordAdded(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addListener(DatabaseListener listener) {
        if (listener != null) {
            listeners.append(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRental(Rental rental) throws Exception {
        if (rental.getStatus() != RentalStatus.OPEN) {
            throw new DatabaseException("Rental status is invalid.");
        }

        if (rental.getVehicle().getStatus() != VehicleStatus.AVAILABLE) {
            throw new DatabaseException("Vehicle status is invalid.");
        }

        rental.getVehicle().setStatus(VehicleStatus.CHECKED_OUT);

        rentals.append(rental);

        onRecordAdded(rental);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVehicle(Vehicle vehicle) throws Exception {
        vehicles.append(vehicle);

        onRecordAdded(vehicle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query<Customer> queryCustomers() throws Exception {
        return new Query<Customer>(customers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query<Employee> queryEmployees() throws Exception {
        return new Query<Employee>(employees);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Query<Rental> queryRentals() throws Exception {
        return new Query<Rental>(rentals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query<Vehicle> queryVehicles() throws Exception {
        return new Query<Vehicle>(vehicles);
    }

    /**
     * Calls DatabaseListeners after a new record is added.
     * @param record
     */
    private void onRecordAdded(Object record) {
        for (DatabaseListener listener : listeners) {
            listener.onRecordAdded(record);
        }
    }

    /**
     * Calls DatabaseListeners after an existing record is updated.
     * @param record
     */
    private void onRecordUpdated(Object record) {
        for (DatabaseListener listener : listeners) {
            listener.onRecordUpdated(record);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCustomer(Customer customer) throws Exception {
        onRecordUpdated(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(Employee employee) throws Exception {
        onRecordUpdated(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRental(Rental rental) throws Exception {
        onRecordUpdated(rental);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVehicle(Vehicle vehicle) throws Exception {
        onRecordUpdated(vehicle);
    }
}
