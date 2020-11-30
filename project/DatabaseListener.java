//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DatabaseListener.java
//  Listener for database events.
//  Visual Studio Code

public interface DatabaseListener
{
    /**
     * Called after a database adds a new record.
     */
    void onRecordAdded();
}
