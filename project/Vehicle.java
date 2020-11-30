//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Vehicle.java
//  Rental vehicle class.
//  Visual Studio Code

public class Vehicle
{
    private Tier tier;
    private int id;
    private String make;
    private String model;
    private Status status;
    private String vin;

    /**
     * Constructs a Vehicle.
     * @param   id   Unique vehicle ID (database key)
     * @param   make Vehicle make (manufacturer)
     * @param   model Vehicle model
     * @param   vin Vehicle Identification Number
     * @param   tier Tier
     * @param   status Status
     */
    public Vehicle(
        int id,
        String make,
        String model,
        String vin,
        Tier tier,
        Status status
    ) {
        this.id = id;
        this.tier = tier;
        this.make = make;
        this.model = model;
        this.status = status;
        this.vin = vin;
    }

    /**
     * Calculates the rental price of this vehicle.
     * @param   miles   total mileage vehicle was driven during rental
     * @return  price
     */
    public double calculatePrice(double miles) {
        return Math.max(0, miles) * Tier.getPricePerMile(tier);
    }

    /**
     * Gets the ID (database key) of this vehicle.
     * @return  ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the make (manufacturer) of this vehicle.
     * @return  Vehicle make (manufacturer)
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the model of this vehicle.
     * @return  Vehicle model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the price-per-mile of this vehicle.
     * @return  price-per-mile
     */
    public double getPricePerMile() {
        return Tier.getPricePerMile(tier);
    }

    /**
     * Gets the status of the vehicle.
     * i.e. if it's available, in maintaince, rented, etc.
     * @return  status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the tier of this vehicle.
     * @return  tier
     */
    public Tier getTier() {
        return tier;
    }

    /**
     * Sets the status of this vehicle.
     * @param status new status of vehicle
     * @return  status
     */
    void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the Vehicle Identification Number of this vehicle.
     * @return  VIN
     */
    public String getVin() {
        return vin;
    }
}
