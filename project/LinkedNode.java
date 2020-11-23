//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  LinkedNode.java
//  Node class for linked-list elements.
//  Visual Studio Code

public class LinkedNode<T>
{
    private T element;
    private LinkedNode<T> next;
    private LinkedNode<T> previous;

    //  ------------------------------------------------------------------------
    public LinkedNode(T elem)
    {
        element = elem;
        next = null;
        previous = null;
    }

    //  ------------------------------------------------------------------------
    public T getElement() {
        return element;
    }

    //  ------------------------------------------------------------------------
    public LinkedNode<T> getNext()
    {
        return next;
    }

    //  ------------------------------------------------------------------------
    public LinkedNode<T> getPrevious()
    {
        return previous;
    }

    //  ------------------------------------------------------------------------
    public boolean hasNext() {
        return next != null;
    }

    //  ------------------------------------------------------------------------
    public boolean hasPrevious() {
        return previous != null;
    }

    //  ------------------------------------------------------------------------
    public void setNext(LinkedNode<T> node)
    {
        next = node;
    }

    //  ------------------------------------------------------------------------
    public void setPrevious(LinkedNode<T> node)
    {
        previous = node;
    }
}
