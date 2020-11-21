//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Employee.java
//  Rental company employee class.
//  Visual Studio Code

public class Employee
{
    private int id;
    private String name;
    private String title;
    private UserLevel level;

    //  ------------------------------------------------------------------------
    public Employee(int id, String name, String title, UserLevel level) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.level = level;
    }

    //  ------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    //  ------------------------------------------------------------------------
    public UserLevel getLevel() {
        return level;
    }

    //  ------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    //  ------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }
}
