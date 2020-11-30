//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  VehicleStatus.java
//  Rental vehicle status.
//  Visual Studio Code

public enum VehicleStatus
{
    /**
     * Vehicle is available for rental.
     */
    AVAILABLE("Available"),
    /**
     * Vehicle is under maintainence and not available for rental.
     */
    MAINTENANCE("Maintenance"),
    /**
     * Vehicle is currently being rented.
     */
    CHECKED_OUT("Checked-Out");

    //  Database ID values
    final static int AVAILBLE_INT    = 0;
    final static int MAINTENANCE_INT = 1;
    final static int CHECKED_OUT_INT = 2;

    /**
     * Display string describing this enum.
     */
    private final String string;

    /**
     * Constructs a VehicleStatus enum.
     * @param string String for toString method to return
     */
    private VehicleStatus(String string) {
        this.string = string;
    }

    /**
     * Converts an int to a VehicleStatus enum.
     * @param value int value to convert from
     * @return status VehicleStatus matching specified int
     */
    static public VehicleStatus fromInt(int value) {
        switch (value) {
            default:
            case AVAILBLE_INT:
                return AVAILABLE;
            case MAINTENANCE_INT:
                return MAINTENANCE;
            case CHECKED_OUT_INT:
                return CHECKED_OUT;
        }
    }

    /**
     * Converts this VehicleStatus enum to an int.
     * @return int value matching this enum
     */
    public int toInt() {
        switch (this) {
            default:
            case AVAILABLE:
                return AVAILBLE_INT;
            case MAINTENANCE:
                return MAINTENANCE_INT;
            case CHECKED_OUT:
                return CHECKED_OUT_INT;
        }
    }

    /**
     * Returns a display String for this enum.
     * @return string
     */
    @Override
    public String toString() {
        return this.string;
    }
}
