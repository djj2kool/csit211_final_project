//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedNodeIterator.java
//  Linked-node iterator class.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.Iterator;

public class LinkedNodeIterator<T> implements Iterator<T>
{
    LinkedNode<T> current;

    /**
     * Constructs a LinkedNodeIterator.
     * @param head the first node in the linked node collection
     */
    LinkedNodeIterator(LinkedNode<T> head) {
        this.current = head;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        T element = current.getElement();
        current = current.getNext();
        return element;
    }
}

