//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Filter.java
//  Filter class.
//  Visual Studio Code

import java.util.function.Predicate;

public class Filter<T>
{
    private String name;
    private Predicate<T> predicate;

    //  ------------------------------------------------------------------------
    public Filter(String name, Predicate<T> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    //  ------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    //  ------------------------------------------------------------------------
    public boolean test(T element) {
        return predicate.test(element);
    }

    //  ------------------------------------------------------------------------
    public String toString() {
        return name;
    }
}
