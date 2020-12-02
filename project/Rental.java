//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Rental.java
//  Rental class representing a rental agreement (sales order).
//  Visual Studio Code

public class Rental
{
    /** Used when database generated ID is not yet assigned */
    private final static int ID_UNASSIGNED = -1;

    /** Unique ID (database key) */
    private int id;
    private double price;
    private RentalStatus status;
    private Customer customer;
    private Vehicle vehicle;

    /**
     * Constructs a Rental instance when the ID is known (e.g. from a database
     * record)
     * @param id Unique ID (database key)
     * @param status
     * @param customer
     * @param vehicle
     */
    public Rental(
        int id,
        RentalStatus status,
        Customer customer,
        Vehicle vehicle
    ) {
        this.id = id;
        this.price = 0;
        this.customer = customer;
        this.status = status;
        this.vehicle = vehicle;
    }

    /**
     * Constructs a Rental instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     * @param customer
     * @param vehicle
     */
    public Rental(Customer customer, Vehicle vehicle) {
        this.id = ID_UNASSIGNED;
        this.price = 0;
        this.customer = customer;
        this.status = RentalStatus.OPEN;
        this.vehicle = vehicle;
    }

    /**
     * Gets the unique ID (database key)
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the customer.
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the rental price.
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the rental status.
     * @return RentalStatus
     */
    public RentalStatus getStatus() {
        return status;
    }

    /**
     * Sets the rental price.
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the rental status.
     * @param status
     */
    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    /**
     *  Gets the vehicle.
     *  @return Vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }
}
