//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Filter.java
//  Filter class.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.function.Predicate;

public class Filter<T>
{
    /** Descriptive name */
    private String name;
    /** Predicate used by the filter when testing elements */
    private Predicate<T> predicate;

    /**
     * Constructs a Filter.
     * @param name the descriptive name
     * @param predicate the predicate used by the filter when testing elements
     */
    public Filter(String name, Predicate<T> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    /**
     * Gets the descriptive name of this filter.
     * @return descriptive name of this filter
     */
    public String getName() {
        return name;
    }

    /**
     * Tests an element against this filter's predicate.
     * @param element the element to test
     * @return true if the element passes the predicate, false otherwise
     */
    public boolean test(T element) {
        return predicate.test(element);
    }

    /**
     * Returns a string representation of the filter.
     * @return a string representation of the filter
     */
    public String toString() {
        return name;
    }
}
