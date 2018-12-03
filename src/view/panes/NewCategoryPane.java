package view.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.domain.Facade;

import java.util.List;

public class NewCategoryPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox categoryField;
	private Facade facade;

	public NewCategoryPane(Facade facade) {
		setFacade(facade);
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		List<String> titles = facade.getCategoryTitles();
		ObservableList<String> categoryTitleList = FXCollections.observableArrayList(titles);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Title:"), 0, 0, 1, 1);
		titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);

		this.add(new Label("Description:"), 0, 1, 1, 1);
		descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);

		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		categoryField = new ComboBox(categoryTitleList);
		this.add(categoryField, 1, 2, 1, 1);

		btnCancel = new Button("Cancel");
		this.add(btnCancel, 0, 3, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		this.add(btnOK, 1, 3, 1, 1);
	}

	public void setSaveButtonHandler(EventHandler<ActionEvent> saveButtonHandler) {
		btnOK.setOnAction(saveButtonHandler);
	}

	public void setCancelButtonHandler(EventHandler<ActionEvent> cancelButtonHandler) {
		btnCancel.setOnAction(cancelButtonHandler);
	}

	public TextField getTitleField() {
		return titleField;
	}

	public void setTitleField(TextField titleField) {
		this.titleField = titleField;
	}

	public TextField getDescriptionField() {
		return descriptionField;
	}

	public void setDescriptionField(TextField descriptionField) {
		this.descriptionField = descriptionField;
	}

	public ComboBox getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(ComboBox categoryField) {
		this.categoryField = categoryField;
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}
}
