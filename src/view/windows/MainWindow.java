package view.windows;

import controller.CategoryController;
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

public class MainWindow extends Stage {

	private BorderPane mainPane;
	private Stage stage;

	public MainWindow(Stage stage) {
		this.setStage(stage);

		CategoryController categoryController = new CategoryController();

		QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane();

		QuestionDetailPane questionDetailPane = new QuestionDetailPane();

		CategoryOverviewPane categoryOverviewPanel = new CategoryOverviewPane(categoryController);
		CategoryDetailPane categoryDetailPanel = new CategoryDetailPane();

		Group root = new Group();
		Scene scene = new Scene(root, 750, 400);

		TestPane testPane = new TestPane();
		MessagePane messagePane = new MessagePane();

		BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		this.setMainPane(borderPane);

		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public BorderPane getMainPane() {
		return this.mainPane;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void start() {
		this.getStage().show();
	}

	public void stop() {
		this.getStage().close();
	}

}