//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedList.java
//  Linked-list collection of elements.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>, ListADT<T> {
    LinkedNode<T> head = null;
    LinkedNode<T> tail = null;

    // ------------------------------------------------------------------------
    @SafeVarargs
    LinkedList(T ... elements) {
        for (T element : elements) {
            append(element);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void append(T element) {
        LinkedNode<T> node = new LinkedNode<>(element);

        if (head == null) {
            head = new LinkedNode<>(element);
            tail = head;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void appendAll(Iterable<T> elements) {
        for (T element : elements) {
            append(element);
        }
    }

    /**
     * Recursive node counting function.
     * @param node
     * @return
     */
    // ------------------------------------------------------------------------
    private int getCount(LinkedNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getCount(node.getNext());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedNodeIterator<T>(head);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return getCount(head);
    }
}
