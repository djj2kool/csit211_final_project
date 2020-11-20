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

    //  ------------------------------------------------------------------------
    public Vehicle(int id, String make, String model, Tier tier) {
        this.id = id;
        this.tier = tier;
        this.make = make;
        this.model = model;
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public String getMake() {
        return make;
    }

    //  ------------------------------------------------------------------------
    public String getModel() {
        return model;
    }

    //  ------------------------------------------------------------------------
    public double calculatePrice(double miles) {
        return miles * Tier.getPricePerMile(tier);
    }

    //  ------------------------------------------------------------------------
    public Tier getTier() {
        return tier;
    }
}
