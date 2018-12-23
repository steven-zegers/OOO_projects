package controller;

import javafx.stage.Stage;
import view.windows.MainWindow;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class MainWindowController {

	MainWindow mainWindow;

	public MainWindowController(Stage primaryStage) {
		this.mainWindow = new MainWindow(primaryStage);
		this.mainWindow.start();
	}

}