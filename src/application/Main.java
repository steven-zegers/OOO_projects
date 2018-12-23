package application;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

	    new MainWindowController(primaryStage);

	}

	public static void main(String[] args) {
        try {
            Application.launch(args);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}
}
