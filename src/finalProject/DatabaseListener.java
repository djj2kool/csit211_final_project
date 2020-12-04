//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DatabaseListener.java
//  Listener for database events.
//  Eclipse, Visual Studio Code

package finalProject;

public interface DatabaseListener
{
    /**
     * Called after a database adds a new record.
     */
    void onRecordAdded(Object record);
}
