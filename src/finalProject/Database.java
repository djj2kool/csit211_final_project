//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MockDatabase.java
//  Application database interface.
//  Eclipse, Visual Studio Code

package finalProject;

public interface Database
{
    /**
     * Adds a new Customer record.
     * @param customer Customer to add to database
     * @throws Exception
     */
    public void addCustomer(Customer customer) throws Exception;

    /**
     * Adds a new Employee record.
     * @param employee Employee to add to database
     * @throws Exception
     */
    public void addEmployee(Employee employee) throws Exception;

    /**
     * Adds a DatabaseListener to be called after events.
     * @param listener
     */
    public void addListener(DatabaseListener listener);

    /**
     * Adds a new Rental record.
     * @param rental Rental to add to database
     * @throws Exception
     */
    public void addRental(Rental rental) throws Exception;

    /**
     * Adds a new Vehicle record.
     * @param vehicle Vehicle to add to database
     * @throws Exception
     */
    public void addVehicle(Vehicle vehicle) throws Exception;

    /**
     * Returns a Query containing Customer records from the database.
     * @return Query containing Customer records from the database.
     * @throws Exception
     */
    public Query<Customer> queryCustomers() throws Exception;

    /**
     * Returns a Query containing Employee records from the database.
     * @return Query containing Employee records from the database.
     * @throws Exception
     */
    public Query<Employee> queryEmployees() throws Exception;

    /**
     * Returns a Query containing Rental records from the database.
     * @return Query containing Rental records from the database.
     * @throws Exception
     */
    public Query<Rental> queryRentals() throws Exception;

    /**
     * Returns a Query containing Vehicle records from the database.
     * @return Query containing Vehicle records from the database.
     * @throws Exception
     */
    public Query<Vehicle> queryVehicles() throws Exception;

    /**
     * Updates a customer record.
     * @param customer
     * @throws Exception
     */
    public void updateCustomer(Customer customer) throws Exception;

    /**
     * Updates a employee record.
     * @param employee
     * @throws Exception
     */
    public void updateEmployee(Employee employee) throws Exception;

    /**
     * Updates a rental record.
     * @param rental
     * @throws Exception
     */
    public void updateRental(Rental rental) throws Exception;

    /**
     * Updates a vehicle record.
     * @param vehicle
     * @throws Exception
     */
    public void updateVehicle(Vehicle vehicle) throws Exception;
}
