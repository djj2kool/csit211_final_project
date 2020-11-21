//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Status.java
//  Rental vehicle status.
//  Visual Studio Code

public enum Status
{
    AVAILABLE("Available"),
    MAINTENANCE("Maintenance"),
    CHECKED_OUT("Checked-Out");

    final static int AVAILBLE_INT    = 0;
    final static int MAINTENANCE_INT = 1;
    final static int CHECKED_OUT_INT = 2;

    private final String string;

    //  ------------------------------------------------------------------------
    private Status(String string) {
        this.string = string;
    }

    //  ------------------------------------------------------------------------
    static public Status intToStatus(int value) {
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

    //  ------------------------------------------------------------------------
    @Override
    public String toString() {
        return this.string;
    }
}
