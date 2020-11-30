//  Louis Slavotinek
//  DatabaseException.java
//  Class for database transaction exceptions.
//  Visual Studio Code

public class DatabaseException extends RuntimeException
{
    //  ------------------------------------------------------------------------
    public DatabaseException(String error)
    {
        super(error);
    }
}
