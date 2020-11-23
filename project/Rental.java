//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Rental.java
//  Rental class representing a rental agreement (sales order).
//  Visual Studio Code

public class Rental
{
    private int id;
    private RentalStatus status;
    private Customer customer;
    private Vehicle vehicle;

    //  ------------------------------------------------------------------------
    public Rental(
        int id,
        RentalStatus status,
        Customer customer,
        Vehicle vehicle
    ) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.vehicle = vehicle;
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public Customer getCustomer() {
        return customer;
    }

    //  ------------------------------------------------------------------------
    public RentalStatus getStatus() {
        return status;
    }

    //  ------------------------------------------------------------------------
    void setStatus(RentalStatus status) {
        this.status = status;
    }

    //  ------------------------------------------------------------------------
    public Vehicle getVehicle() {
        return vehicle;
    }
}
