package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;
import view.windows.MainWindow;

public class MainWindowController {

	MainWindow mainWindow;

	public MainWindowController(Stage primaryStage) {

		this.mainWindow = new MainWindow(primaryStage);
		this.mainWindow.start();



	}

}