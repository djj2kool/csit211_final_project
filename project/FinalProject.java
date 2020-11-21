//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  FinalProject.java
//  Application entry.
//  Visual Studio Code

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalProject extends Application
{
    //  ------------------------------------------------------------------------
    public static void main(String[] args) {
        try {
            List<Employee> employees = Database.queryEmployees();
            List<Vehicle> vehicles = Database.queryVehicles();

            for (Employee employee : employees) {
                System.out.format(
                    "%d: %s - %s\n",
                    employee.getId(),
                    employee.getName(),
                    employee.getTitle()
                );
            }

            for (Vehicle vehicle : vehicles) {
                System.out.format(
                    "%d: %s %s - $%.2f per-mile\n",
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.calculatePrice(1)
                );
            }
        }
        catch (Exception ex) {
            System.out.println("Failed to connect to database.");
            System.out.println(ex);
        }

        launch(args);
    }

    //  ------------------------------------------------------------------------
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CDLR Corporation - Vehicle Rentals");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
