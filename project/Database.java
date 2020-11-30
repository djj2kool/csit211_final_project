//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MockDatabase.java
//  Application database interface.
//  Visual Studio Code

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
}
