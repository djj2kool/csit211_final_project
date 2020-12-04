//  Louis Slavotinek
//  DatabaseException.java
//  Class for database transaction exceptions.
//  Eclipse, Visual Studio Code

package finalProject;

public class DatabaseException extends RuntimeException
{
    //  ------------------------------------------------------------------------
    public DatabaseException(String error)
    {
        super(error);
    }
}
