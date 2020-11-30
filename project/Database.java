//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  MockDatabase.java
//  Application database interface.
//  Visual Studio Code

public interface Database
{
    public void addCustomer(Customer customer) throws Exception;
    public void addEmployee(Employee employee) throws Exception;
    public void addListener(DatabaseListener listener);
    public void addRental(Rental rental) throws Exception;
    public Query<Customer> queryCustomers() throws Exception;
    public Query<Employee> queryEmployees() throws Exception;
    public Query<Rental> queryRentals() throws Exception;
    public Query<Vehicle> queryVehicles() throws Exception;
}
