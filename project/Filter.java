//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Filter.java
//  Filter class.
//  Visual Studio Code

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Filter<T>
{
    private String name;
    private Predicate<T> predicate;

    public Filter(String name, Predicate<T> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public void apply(List<T> list) {
        T item;
        Iterator<T> itr = list.iterator();

        while (itr.hasNext()) {
            item = itr.next();
            if (!predicate.test(item)) {
                itr.remove();
            }
        }
    }

    public String getName() {
        return name;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public String toString() {
        return name;
    }
}
