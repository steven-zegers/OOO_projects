package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panes.NewCategoryPane;

public class NewCategoryWindow extends Stage {

	private NewCategoryPane pane;
	private Stage stage;

	public NewCategoryWindow(Stage stage) {
		this.setStage(stage);
		this.setPane(new NewCategoryPane());

		Scene mainScene = new Scene(this.getPane(), 250,150);
		this.getStage().setTitle("New Category");
		this.getStage().setScene(mainScene);
		sizeToScene();
	}

	public void setPane(NewCategoryPane categoryDetailPane) {
		this.pane = categoryDetailPane;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public NewCategoryPane getPane() {
		return this.pane;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setSaveButtonHandler(EventHandler<ActionEvent> saveButtonHandler) {
		this.getPane().setSaveButtonHandler(saveButtonHandler);
	}

	public void setCancelButtonHandler(EventHandler<ActionEvent> cancelButtonHandler) {
		this.getPane().setCancelButtonHandler(cancelButtonHandler);
	}

	public void start() {
		this.getStage().show();
	}

	public void stop() {
		this.getStage().close();
	}

}