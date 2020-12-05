//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Main.java
//  Application entry point.
//  Eclipse, Visual Studio Code

package finalProject;

import javafx.application.Application;

public class Main {
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		//	Launching this way removes the need to use --module-path and
		//	--add-module with JavaFX when running from an IDE.
		Application.launch(App.class);
	}
}
