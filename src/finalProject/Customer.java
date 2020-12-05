//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Customer.java
//  Rental company customer class.
//  Eclipse, Visual Studio Code

package finalProject;

public class Customer
{
    /** Used when database generated ID is not yet assigned */
    private final static int ID_UNASSIGNED = -1;

    /** Unique ID (database key) */
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    /**
     * Constructs a Customer instance when the ID is known (e.g. from a database
     * record)
     * @param id
     * @param firstName
     * @param lastName
     * @param phone
     */
    public Customer(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Constructs a Customer instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     * @param firstName
     * @param lastName
     * @param phone
     */
    public Customer(String firstName, String lastName, String phone) {
        this.id = ID_UNASSIGNED;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Gets the first name of this customer.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the ID (database key) of this customer.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the last name of this customer.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the full name of this customer.
     * @return name
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Gets the phone number of this customer.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the first name of this customer.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of this customer.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the phone number of this customer.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
