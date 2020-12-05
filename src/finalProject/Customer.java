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
    private String name;
    private String phone;

    /**
     * Constructs a Customer instance when the ID is known (e.g. from a database
     * record)
     * @param id
     * @param name
     * @param phone
     */
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Constructs a Customer instance when the ID is not yet known (e.g. when
     * creating an object that will be added to the database later)
     * @param name
     * @param phone
     */
    public Customer(String name, String phone) {
        this.id = ID_UNASSIGNED;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Gets the ID (database key) of this customer.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the full name of this customer.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number of this customer.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the name of this customer.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the phone number of this customer.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
