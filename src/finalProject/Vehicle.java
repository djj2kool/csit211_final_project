//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Vehicle.java
//  Rental vehicle class.
//  Eclipse, Visual Studio Code

package finalProject;

public class Vehicle
{
    /** Used when database generated ID is not yet assigned */
    private final static int ID_UNASSIGNED = -1;

    private Tier tier;
    /** Unique ID (database key) */
    private int id;
    private String make;
    private String model;
    private VehicleStatus status;
    private String vin;

    /**
     * Constructs a Vehicle instance when the ID is known (e.g. from a database
     * record)
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
        VehicleStatus status
    ) {
        this.id = id;
        this.tier = tier;
        this.make = make;
        this.model = model;
        this.status = status;
        this.vin = vin;
    }

    /**
     * Constructs a Vehicle instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     * @param   make Vehicle make (manufacturer)
     * @param   model Vehicle model
     * @param   vin Vehicle Identification Number
     * @param   tier
     */
    public Vehicle(
        String make,
        String model,
        String vin,
        Tier tier
    ) {
        this.id = ID_UNASSIGNED;
        this.tier = tier;
        this.make = make;
        this.model = model;
        this.status = VehicleStatus.AVAILABLE;
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
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Gets the tier of this vehicle.
     * @return  tier
     */
    public Tier getTier() {
        return tier;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Sets the status of this vehicle.
     * @param status new status of vehicle
     * @return  status
     */
    void setStatus(VehicleStatus status) {
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
