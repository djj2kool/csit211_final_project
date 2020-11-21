//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  UserLevel.java
//  Rental company user privileges.
//  Visual Studio Code

public enum UserLevel
{
    ASSOCIATE("Associate"),
    EXECUTIVE("Executive"),
    ADMIN("Admin");

    //  Database ID values
    static final int ASSOCIATE_INT = 1;
    static final int EXECUTIVE_INT = 2;
    static final int ADMIN_INT     = 3;

    private final String string;

    //  ------------------------------------------------------------------------
    private UserLevel(String string) {
        this.string = string;
    }

    //  ------------------------------------------------------------------------
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

    //  ------------------------------------------------------------------------
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

    //  ------------------------------------------------------------------------
    @Override
    public String toString() {
        return this.string;
    }
}
