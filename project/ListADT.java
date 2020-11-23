//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  ListADT.java
//  List abstract data type.
//  Visual Studio Code

public interface ListADT<T>
{
    void append(T element);
    void appendAll(Iterable<T> elements);
    boolean isEmpty();
    int size();
}
