//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Customer.java
//  Rental company customer class.
//  Visual Studio Code

public class Customer
{
    private int id;
    private String name;
    private String phone;

    //  ------------------------------------------------------------------------
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    //  ------------------------------------------------------------------------
    public Customer(String name, String phone) {
        this.id = -1;
        this.name = name;
        this.phone = phone;
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    //  ------------------------------------------------------------------------
    public String getPhone() {
        return phone;
    }
}
