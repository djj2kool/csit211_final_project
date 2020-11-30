//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  UserLevel.java
//  Rental company user privileges.
//  Visual Studio Code

public enum UserLevel
{
    /**
     * General employee.
     * Can add customers, rentals, and vehicles.
     */
    ASSOCIATE("Associate"),
    /**
     * Management.
     * Can perform Associate level actions.
     * Can add employees.
     */
    EXECUTIVE("Executive"),
    /**
     * System administrator.
     * Can perform Executive level actions.
     */
    ADMIN("Admin");

    //  Database ID values
    static final int ASSOCIATE_INT = 1;
    static final int EXECUTIVE_INT = 2;
    static final int ADMIN_INT     = 3;

    /**
     * Display string describing this enum.
     */
    private final String string;

    /**
     * Constructs a UserLevel enum.
     * @param string String for toString method to return
     */
    private UserLevel(String string) {
        this.string = string;
    }

    /**
     * Converts an int to a UserLevel enum.
     * @param value int value to convert from
     * @return UserLevel matching specified int
     */
    static public UserLevel fromInt(int value) {
        switch (value) {
            default:
            case ASSOCIATE_INT:
                return ASSOCIATE;
            case EXECUTIVE_INT:
                return EXECUTIVE;
            case ADMIN_INT:
                return ADMIN;
        }
    }

    /**
     * Converts this UserLevel enum to an int.
     * @return int value matching this enum
     */
    public int toInt() {
        switch (this) {
            default:
            case ASSOCIATE:
                return ASSOCIATE_INT;
            case EXECUTIVE:
                return EXECUTIVE_INT;
            case ADMIN:
                return ADMIN_INT;
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
