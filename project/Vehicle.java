//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Vehicle.java
//  Rental vehicle class.
//  Visual Studio Code

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle
{
    //  TODO: StringProperty could be replaced with String if read-only
    private Tier tier;
    private int id;
    private StringProperty make;
    private StringProperty model;
    private Status status;
    private String vin;

    //  ------------------------------------------------------------------------
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
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.status = status;
        this.vin = vin;
    }

    //  ------------------------------------------------------------------------
    public double calculatePrice(double miles) {
        return Math.max(0, miles) * Tier.getPricePerMile(tier);
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public String getMake() {
        return make.get();
    }

    //  ------------------------------------------------------------------------
    public String getModel() {
        return model.get();
    }

    //  ------------------------------------------------------------------------
    public double getPricePerMile() {
        return Tier.getPricePerMile(tier);
    }

    //  ------------------------------------------------------------------------
    public Status getStatus() {
        return status;
    }

    //  ------------------------------------------------------------------------
    public Tier getTier() {
        return tier;
    }

    //  ------------------------------------------------------------------------
    void setStatus(Status status) {
        this.status = status;
    }

    //  ------------------------------------------------------------------------
    public String getVin() {
        return vin;
    }
}
