//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedNode.java
//  Node class for linked-list elements.
//  Eclipse, Visual Studio Code

package finalProject;

public class LinkedNode<T>
{
    private T element;
    private LinkedNode<T> next;
    private LinkedNode<T> previous;

    /**
     * Constructs a LinkedNode.
     * @param element
     */
    public LinkedNode(T element) {
        this.element = element;
        next = null;
        previous = null;
    }

    /**
     * Gets the element contained in this node.
     * @return
     */
    public T getElement() {
        return element;
    }

    /**
     * Gets the next sibling node.
     * @return
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * Gets the previous sibling node.
     * @return
     */
    public LinkedNode<T> getPrevious() {
        return previous;
    }

    /**
     * Checks if this node has a sibling node after it.
     * @return
     */
    public boolean hasNext() {
        return next != null;
    }

    /**
     * Checks if this node has a sibling node before it.
     * @return
     */
    public boolean hasPrevious() {
        return previous != null;
    }

    /**
     * Sets the next sibling node.
     * @return
     */
    public void setNext(LinkedNode<T> node) {
        next = node;
    }

    /**
     * Sets the previous sibling node.
     * @return
     */
    public void setPrevious(LinkedNode<T> node) {
        previous = node;
    }
}
