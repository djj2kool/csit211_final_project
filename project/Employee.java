//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Employee.java
//  Rental company employee class.
//  Visual Studio Code

public class Employee
{
    /** Used when database generated ID is not yet assigned */
    private final static int ID_UNASSIGNED = -1;

    /** Unique ID (database key) */
    private int id;
    private String name;
    private String title;
    private UserLevel level;

    /**
     * Constructs an Employee instance when the ID is known (e.g. from a database
     * record)
     * @param id
     * @param name
     * @param title
     * @param level
     */
    public Employee(int id, String name, String title, UserLevel level) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.level = level;
    }

    /**
     * Constructs an Employee instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     * @param name
     * @param title
     * @param level
     */
    public Employee(String name, String title, UserLevel level) {
        this.id = ID_UNASSIGNED;
        this.name = name;
        this.title = title;
        this.level = level;
    }

    /**
     * Gets the ID (database key) of this employee.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the user access level of this employee.
     * @return UserLevel
     */
    public UserLevel getLevel() {
        return level;
    }

    /**
     * Gets the full name of this employee.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the full title of this employee.
     * @return title
     */
    public String getTitle() {
        return title;
    }
}
