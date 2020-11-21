//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DialogController.java
//  Abstract controller class for dialog controllers.
//  Visual Studio Code

import javafx.stage.Stage;

public abstract class DialogController<T> {
    abstract T getValue();
    abstract void setStage(Stage stage);
}
