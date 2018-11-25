package view.windows;

import controller.CategoryOverviewController;
import controller.QuestionOverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panes.AssesMainPane;
import view.panes.CategoryOverviewPane;
import view.panes.MessagePane;
import view.panes.QuestionOverviewPane;
import view.panes.TestPane;

public class MainWindow extends Stage {

	private BorderPane mainPane;
	private Stage stage;

	public MainWindow(Stage stage) {
		this.setStage(stage);

		QuestionOverviewController questionOverviewController = new QuestionOverviewController(stage);
		QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(questionOverviewController);
		questionOverviewController.setPane(questionOverviewPane);

		CategoryOverviewController categoryOverviewController = new CategoryOverviewController();
		CategoryOverviewPane categoryOverviewPane = new CategoryOverviewPane();
		categoryOverviewController.setPane(categoryOverviewPane);

		TestPane testPane = new TestPane();
		MessagePane messagePane = new MessagePane();

		Group root = new Group();
		Scene scene = new Scene(root, 750, 400);

		BorderPane borderPane = new AssesMainPane(messagePane, categoryOverviewPane, questionOverviewPane);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		this.setMainPane(borderPane);

		root.getChildren().add(borderPane);
		this.stage.setTitle("Main window");
		this.stage.setScene(scene);
		this.stage.sizeToScene();
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