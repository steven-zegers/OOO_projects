package view.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.domain.Facade;

public class NewQuestionPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextArea statementsArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;
	private Facade facade;

	public NewQuestionPane(Facade facade) {
		setFacade(facade);
		this.setPrefHeight(300);
		this.setPrefWidth(320);
		ObservableList<String> categoryTitleList = FXCollections.observableArrayList(facade.getCategoryTitles());
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		this.add(questionField, 1, 0, 2, 1);

		this.add(new Label("Statement: "), 0, 1, 1, 1);
		statementField = new TextField();
		this.add(statementField, 1, 1, 2, 1);

		this.add(new Label("Statements: "), 0, 2, 1, 1);
		statementsArea = new TextArea();
		statementsArea.setPrefRowCount(5);
		statementsArea.setEditable(false);
		this.add(statementsArea, 1, 2, 2, 5);

		Pane addRemove = new HBox();
		btnAdd = new Button("add");
		addRemove.getChildren().add(btnAdd);

		btnRemove = new Button("remove");
		addRemove.getChildren().add(btnRemove);
		add(addRemove, 1, 8, 2, 1);

		add(new Label("Category: "), 0, 9, 1, 1);
		categoryField = new ComboBox(categoryTitleList);
		categoryField.getSelectionModel().selectFirst();
		add(categoryField, 1, 9, 2, 1);

		add(new Label("Feedback: "), 0, 10, 1, 1);
		feedbackField = new TextField();
		add(feedbackField, 1, 10, 2, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 11, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setText("Save");
		add(btnOK, 1, 11, 2, 1);
		
	}

	public void setAddButtonHandler(EventHandler<ActionEvent> addButtonHandler) {
		btnAdd.setOnAction(addButtonHandler);
	}

	public void setRemoveButtonHandler(EventHandler<ActionEvent> removeButtonHandler) {
		btnRemove.setOnAction(removeButtonHandler);
	}

	public void setSaveButtonHandler(EventHandler<ActionEvent> saveButtonHandler) {
		btnOK.setOnAction(saveButtonHandler);
	}

	public void setCancelButtonHandler(EventHandler<ActionEvent> cancelButtonHandler) {
		btnCancel.setOnAction(cancelButtonHandler);
	}

	public TextArea getStatementsArea() {
		return statementsArea;
	}

	public void setStatementsArea(TextArea statementsArea) {
		this.statementsArea = statementsArea;
	}

	public TextField getQuestionField() {
		return questionField;
	}

	public void setQuestionField(TextField questionField) {
		this.questionField = questionField;
	}

	public TextField getStatementField() {
		return statementField;
	}

	public void setStatementField(TextField statementField) {
		this.statementField = statementField;
	}

	public TextField getFeedbackField() {
		return feedbackField;
	}

	public void setFeedbackField(TextField feedbackField) {
		this.feedbackField = feedbackField;
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
