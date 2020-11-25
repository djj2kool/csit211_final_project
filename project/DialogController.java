//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  DialogController.java
//  Abstract controller class for dialog controllers.
//  Visual Studio Code

import javafx.stage.Stage;

public abstract class DialogController<T> {
    protected Database database;

    public abstract T getValue();

    //  ------------------------------------------------------------------------
    public void setDatabase(Database database) {
        this.database = database;
    }

    public abstract void setStage(Stage stage);
}
