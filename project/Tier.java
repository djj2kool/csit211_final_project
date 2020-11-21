//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Tier.java
//  Rental vehicle tiers used to determine pricing.
//  Visual Studio Code

public enum Tier
{
    ECONOMY("Economy"),
    COMPACT("Compact"),
    STANDARD("Standard"),
    FULL_SIZE("Full-Size"),
    PREMIUM("Premium"),
    LUXURY("Luxury");

    //  Database ID values
    static final int ECONOMY_INT   = 1;
    static final int COMPACT_INT   = 2;
    static final int FULL_SIZE_INT = 3;
    static final int STANDARD_INT  = 4;
    static final int PREMIUM_INT   = 5;
    static final int LUXURY_INT    = 6;

    //  Price-per-mileage in USD
    static final double ECONOMY_PPM   = 0.12;
    static final double COMPACT_PPM   = 0.15;
    static final double STANDARD_PPM  = 0.20;
    static final double FULL_SIZE_PPM = 0.25;
    static final double PREMIUM_PPM   = 0.30;
    static final double LUXURY_PPM    = 0.40;

    private final String string;

    //  ------------------------------------------------------------------------
    private Tier(String string) {
        this.string = string;
    }

    //  ------------------------------------------------------------------------
    static public Tier intToTier(int value) {
        switch (value) {
            default:
            case ECONOMY_INT:
                return ECONOMY;
            case COMPACT_INT:
                return COMPACT;
            case FULL_SIZE_INT:
                return FULL_SIZE;
            case STANDARD_INT:
                return STANDARD;
            case PREMIUM_INT:
                return PREMIUM;
            case LUXURY_INT:
                return LUXURY;
        }
    }

    //  ------------------------------------------------------------------------
    static public double getPricePerMile(Tier tier)
    {
        switch (tier)
        {
            default:
            case ECONOMY:
                return ECONOMY_PPM;
            case COMPACT:
                return COMPACT_PPM;
            case FULL_SIZE:
                return FULL_SIZE_PPM;
            case STANDARD:
                return STANDARD_PPM;
            case PREMIUM:
                return PREMIUM_PPM;
            case LUXURY:
                return LUXURY_PPM;
        }
    }

    //  ------------------------------------------------------------------------
    @Override
    public String toString() {
        return this.string;
    }
}
