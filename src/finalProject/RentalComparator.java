//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  RentalComparator.java
//  Comparator for sorting rentals.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.Comparator;

public class RentalComparator implements Comparator<Rental> {
    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Rental a, Rental b) {
        Customer customerA = a.getCustomer();
        Customer customerB = b.getCustomer();
        int last = customerA.getLastName().compareTo(customerB.getLastName());

        if (last == 0) {
            return customerA.getFirstName().compareTo(customerB.getFirstName());
        }

        return last;
    }
}
