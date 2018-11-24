package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.db.QuestionDB;
import model.domain.Category;
import model.domain.Question;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {

	public static void main(String[] args) {
		try
		{
			Application.launch(args);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		new MainWindowController(primaryStage);
	}

}