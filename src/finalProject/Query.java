//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Query.java
//  Query collection: filterable read-only collection of elements.
//  Eclipse, Visual Studio Code

package finalProject;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

//  Implements Collection so Query class can be easily used to populate TableViews.
public class Query<T> implements Collection<T>
{
    LinkedList<T> list;

    //  ------------------------------------------------------------------------
    public Query(Iterable<T> elements) {
        list = new LinkedList<T>();
        list.appendAll(elements);
    }

    //  ------------------------------------------------------------------------
    public boolean add(T element) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean addAll(Collection<? extends T> collection) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public void clear() {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean contains(Object obj) {
        //  For Collection interface.
        //  TODO: This could be implemented if needed.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean containsAll(Collection<?> collection) {
        //  For Collection interface.
        //  TODO: This could be implemented if needed.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //  ------------------------------------------------------------------------
    public Query<T> filter(Filter<T> filter) {
        T item;
        Iterator<T> itr = list.iterator();
        LinkedList<T> filteredList = new LinkedList<T>();

        while (itr.hasNext()) {
            item = itr.next();
            if (filter.test(item)) {
                filteredList.append(item);
            }
        }

        return new Query<T>(filteredList);
    }

    // ------------------------------------------------------------------------
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    //  ------------------------------------------------------------------------
    public boolean remove(Object obj) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean removeAll(Collection<?> collection) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public boolean retainAll(Collection<?> collection) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public int size() {
        return list.size();
    }

    //  ------------------------------------------------------------------------
    public Object[] toArray() {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    public <E> E[] toArray(E[] a) {
        //  For Collection interface.
        //  Query should be read-only.
        throw new UnsupportedOperationException();
    }

    //  ------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public T[] toArrayT(Class<T> clazz) {
        int index = 0;
        int length = size();
        T[] a = (T[])(Array.newInstance(clazz, length));

        for (T element : this) {
            a[index++] = element;
        }

        return a;
    }
}
