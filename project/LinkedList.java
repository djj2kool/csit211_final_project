//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedList.java
//  Linked-list collection of elements.
//  Visual Studio Code

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

    // ------------------------------------------------------------------------
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

    // ------------------------------------------------------------------------
    public void appendAll(Iterable<T> elements) {
        for (T element : elements) {
            append(element);
        }
    }

    // ------------------------------------------------------------------------
    private int getCount(LinkedNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getCount(node.getNext());
        }
    }

    // ------------------------------------------------------------------------
    public boolean isEmpty() {
        return head == null;
    }

    // ------------------------------------------------------------------------
    @Override
    public Iterator<T> iterator() {
        return new LinkedNodeIterator<T>(head);
    }

    // ------------------------------------------------------------------------
    public int size() {
        return getCount(head);
    }
}
