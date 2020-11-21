//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Customer.java
//  Rental company customer class.
//  Visual Studio Code

public class Customer
{
    private int id;
    private String name;

    //  ------------------------------------------------------------------------
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public String getName() {
        return name;
    }
}
