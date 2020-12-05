//  Louis Slavotinek
//  RentalException.java
//  Class for rental exceptions.
//  Eclipse, Visual Studio Code

package finalProject;

public class RentalException extends RuntimeException
{
    /**
     * Constructs a RentalException.
     * @param error
     */
    public RentalException(String error)
    {
        super(error);
    }
}
