package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.NewCategoryPane;

public class NewCategoryWindow extends Stage {

	private NewCategoryPane pane;
	private Stage stage;
	private Facade facade;

	public NewCategoryWindow(Stage stage, Facade facade) {
		setFacade(facade);
		this.setStage(stage);
		this.setPane(new NewCategoryPane(facade));

		Scene mainScene = new Scene(this.getPane(), 400,300);
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

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}
}