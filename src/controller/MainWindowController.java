package controller;

import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Test;
import view.windows.MainWindow;

public class MainWindowController {

	MainWindow mainWindow;

	public MainWindowController(Stage primaryStage) {
		Facade facade = new Facade();
		Test test = new Test(facade);
		facade.setCurrentTest(test);
		this.mainWindow = new MainWindow(primaryStage, facade);
		this.mainWindow.start();

	}

}