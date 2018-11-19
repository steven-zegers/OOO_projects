package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.panels.CategoryDetailPane;

public class NewCategoryWindow extends Stage {

	private CategoryDetailPane categoryDetailPane;
	private Stage stage;

	public NewCategoryWindow(Stage stage) {
		this.setCategoryDetailPane(new CategoryDetailPane());
		this.setStage(stage);
	}

	public void setCategoryDetailPane(CategoryDetailPane categoryDetailPane) {
		this.categoryDetailPane = categoryDetailPane;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public CategoryDetailPane getCategoryDetailPane() {
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