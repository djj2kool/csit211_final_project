//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  EditRentalDialogController.java
//  Controller for rental edit dialog.
//  Visual Studio Code

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditRentalDialogController extends RentalDialogController
{
    Rental rental = null;

    //  ------------------------------------------------------------------------
    @Override
    protected void clear() {
        super.clear();
        rental = null;
    }

    //  ------------------------------------------------------------------------
    public Rental getValue() {
        return rental;
    }

    //  ------------------------------------------------------------------------
    @Override
    protected Vehicle getVehicle() {
        return rental == null ? null : rental.getVehicle();
    }

    //  ------------------------------------------------------------------------
    @FXML
    private void onAddButton(ActionEvent event) {
        int mileage;

        try {
            mileage = getMileage();

            if (rental != null && mileage >= 0) {
                rental.setMileage(mileage);
                rental.setStatus(RentalStatus.CLOSED);

                close();
            }
        } catch (Exception ex) {
        }
    }

    //  ------------------------------------------------------------------------
    @Override
    public void setValue(Rental rental) {
        this.rental = rental;
        populateRental(rental);
    }
}
