//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalStatus.java
//  Rental status.
//  Eclipse, Visual Studio Code

package finalProject;

public enum RentalStatus
{
    /**
     * Open (rental in progress)
     */
    OPEN("Open"),
    /**
     * Closed (rental completed)
     */
    CLOSED("Closed");

    final static int OPEN_INT   = 0;
    final static int CLOSED_INT = 1;

    /**
     * Display string describing this enum.
     */
    private final String string;

    /**
     * Constructs a RentalStatus enum.
     * @param string String for toString method to return
     */
    private RentalStatus(String string) {
        this.string = string;
    }

    /**
     * Converts an int to a RentalStatus enum.
     * @param value int value to convert from
     * @return RentalStatus matching specified int
     */
    static public RentalStatus fromInt(int value) {
        switch (value) {
            default:
            case OPEN_INT:
                return OPEN;
            case CLOSED_INT:
                return CLOSED;
        }
    }

    /**
     * Converts this RentalStatus enum to an int.
     * @return int value matching this enum
     */
    public int toInt() {
        switch (this) {
            default:
            case OPEN:
                return OPEN_INT;
            case CLOSED:
                return CLOSED_INT;        }
    }

    /**
     * Returns a string representation of this enum.
     * @return a string representation of the filter
     */
    @Override
    public String toString() {
        return this.string;
    }
}
