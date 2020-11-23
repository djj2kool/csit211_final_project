//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalStatus.java
//  Rental status.
//  Visual Studio Code

public enum RentalStatus
{
    OPEN("Open"),
    CLOSED("Closed");

    final static int OPEN_INT   = 0;
    final static int CLOSED_INT = 1;

    private final String string;

    //  ------------------------------------------------------------------------
    private RentalStatus(String string) {
        this.string = string;
    }

    //  ------------------------------------------------------------------------
    static public RentalStatus fromInt(int value) {
        switch (value) {
            default:
            case OPEN_INT:
                return OPEN;
            case CLOSED_INT:
                return CLOSED;
        }
    }

    //  ------------------------------------------------------------------------
    @Override
    public String toString() {
        return this.string;
    }
}
