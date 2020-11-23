//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedNodeIterator.java
//  Linked-node iterator class.
//  Visual Studio Code

import java.util.Iterator;

public class LinkedNodeIterator<T> implements Iterator<T>
{
    LinkedNode<T> current;

    //  ------------------------------------------------------------------------
    LinkedNodeIterator(LinkedNode<T> head) {
        this.current = head;
    }

    //  ------------------------------------------------------------------------
    public boolean hasNext() {
        return current != null;
    }

    //  ------------------------------------------------------------------------
    public T next() {
        T element = current.getElement();
        current = current.getNext();
        return element;
    }
}

