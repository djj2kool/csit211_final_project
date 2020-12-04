//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  ListADT.java
//  List abstract data type.
//  Eclipse, Visual Studio Code

package finalProject;

public interface ListADT<T>
{
    /**
     * Adds an element to the end of this list.
     * @param element the element to add
     */
    void append(T element);
    /**
     * Adds a collection of elements to the end of this list.
     * @param elements the elements to add
     */
    void appendAll(Iterable<T> elements);
    /**
     * Checks if this list is empty.
     * @return true if empty, false if there are one or more elements
     */
    boolean isEmpty();
    /**
     * Gets the number of elements in this list.
     * @return the number of elements in this list
     */
    int size();
}
