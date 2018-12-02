package view.windows;

import controller.CategoryOverviewController;
import controller.QuestionOverviewController;
import controller.TestOverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.AssesMainPane;
import view.panes.CategoryOverviewPane;
import view.panes.TestOverviewPane;
import view.panes.QuestionOverviewPane;

public class MainWindow extends Stage {

	private BorderPane mainPane;
	private Stage stage;
	private Facade facade;

	public MainWindow(Stage stage) {
		this.setStage(stage);
		this.setFacade(new Facade());

		QuestionOverviewController questionOverviewController = new QuestionOverviewController(this.facade);
		QuestionOverviewPane questionOverviewPane = new QuestionOverviewPane(this.facade);
		questionOverviewController.setPane(questionOverviewPane);

		CategoryOverviewController categoryOverviewController = new CategoryOverviewController(this.facade);
		CategoryOverviewPane categoryOverviewPane = new CategoryOverviewPane(this.facade);
		categoryOverviewController.setPane(categoryOverviewPane);

		TestOverviewController testOverviewController = new TestOverviewController(this.facade);
		TestOverviewPane testOverviewPane = new TestOverviewPane(this.facade);
		testOverviewController.setPane(testOverviewPane);

		Group root = new Group();
		Scene scene = new Scene(root, 750, 400);

		BorderPane borderPane = new AssesMainPane(testOverviewPane, categoryOverviewPane, questionOverviewPane);
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

	public void setFacade(Facade facade) {
		this.facade = facade;
	}
}