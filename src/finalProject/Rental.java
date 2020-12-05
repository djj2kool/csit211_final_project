//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Rental.java
//  Rental class representing a rental agreement (sales order).
//  Eclipse, Visual Studio Code

package finalProject;

public class Rental
{
    /** Used when database generated ID is not yet assigned */
    private final static int ID_UNASSIGNED = -1;

    /** Unique ID (database key) */
    private int id;
    private int mileage;
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
        int mileage,
        RentalStatus status,
        Customer customer,
        Vehicle vehicle
    ) {
        this.id = id;
        this.mileage = mileage;
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
    public Rental(int mileage, Customer customer, Vehicle vehicle) {
        this.id = ID_UNASSIGNED;
        this.mileage = mileage;
        this.customer = customer;
        this.status = RentalStatus.OPEN;
        this.vehicle = vehicle;
    }

    /**
     * Constructs a Rental instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     */
    public Rental() {
        this.id = ID_UNASSIGNED;
        this.mileage = 0;
        this.customer = null;
        this.status = RentalStatus.OPEN;
        this.vehicle = null;
    }

    public void close() {
        if (status == RentalStatus.OPEN) {
            //  Closing the rental releases the vehicle
            if (vehicle != null) {
                vehicle.setStatus(VehicleStatus.AVAILABLE);
            }

            status = RentalStatus.CLOSED;
        }
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
     * Gets the number of miles driven during this rental.
     * @return
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Gets the rental price based on mileage.
     * @return
     */
    public double getPrice() {
        return vehicle.calculatePrice(mileage);
    }

    /**
     * Gets the rental status.
     * @return RentalStatus
     */
    public RentalStatus getStatus() {
        return status;
    }

    /**
     *  Gets the vehicle.
     *  @return Vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isClosed() {
        return status == RentalStatus.CLOSED;
    }

    public boolean isOpen() {
        return status == RentalStatus.OPEN;
    }

    public void open() throws RentalException {
        if (status == RentalStatus.CLOSED) {
            //  Can only reopen a rental if the original vehicle is available
            if (vehicle != null) {
                if (!vehicle.canRent()) {
                    throw new RentalException("Vehicle is not available for rental.");
                }

                vehicle.setStatus(VehicleStatus.CHECKED_OUT);
            }

            status = RentalStatus.OPEN;
        }
    }

    /**
     * Sets the customer.
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Sets the mileage driven during this rental.
     * @param mileage
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Sets the vehicle.
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
