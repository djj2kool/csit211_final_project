//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalComparator.java
//  Comparator for sorting rentals.
//  Visual Studio Code

import java.util.Comparator;

public class RentalComparator implements Comparator<Rental> {
    @Override
    public int compare(Rental a, Rental b) {
        return a.getCustomer().getName().compareTo(
            b.getCustomer().getName());
    }
}
