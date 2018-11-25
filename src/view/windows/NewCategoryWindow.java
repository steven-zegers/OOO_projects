package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.panels.NewCategoryPane;

public class NewCategoryWindow extends Stage {

	private NewCategoryPane categoryDetailPane;
	private Stage stage;

	public NewCategoryWindow(Stage stage) {
		this.setCategoryDetailPane(new NewCategoryPane());
		this.setStage(stage);
	}

	public void setCategoryDetailPane(NewCategoryPane categoryDetailPane) {
		this.categoryDetailPane = categoryDetailPane;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public NewCategoryPane getCategoryDetailPane() {
		return this.categoryDetailPane;
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setSaveButtonHandler(EventHandler<ActionEvent> saveButtonHandler) {
		this.getCategoryDetailPane().setSaveAction(saveButtonHandler);
	}

	public void setCancelButtonHandler(EventHandler<ActionEvent> cancelButtonHandler) {
		this.getCategoryDetailPane().setCancelAction(cancelButtonHandler);
	}

	public void start() {
		this.getStage().show();
	}

	public void stop() {
		this.getStage().close();
	}

}