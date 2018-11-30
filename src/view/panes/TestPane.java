package view.panes;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.domain.Facade;
import model.domain.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Facade facade;
	private ObservableList<String> statements;
	private ListView listView;
	private StackPane root;

	public TestPane (Facade facade){
		setFacade(facade);
		this.setPrefHeight(300);
		this.setPrefWidth(750);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label(facade.getTitleOfCurrentQuestionOfCurrentTest());
		questionField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Listened");
				System.out.println(newValue);
				questionField.setText(newValue);
			}
		});
		add(questionField, 0, 0, 1, 1);

		List<String> statementList = facade.getStatementsOfCurrentQuestion();
		statements = FXCollections.observableArrayList(statementList);
		statementGroup = new ToggleGroup();

		listView = new ListView();
		listView.setItems(statements);
		listView.setCellFactory(param -> new RadioListCell());
		listView.setPrefWidth(600);

		root = new StackPane();
		root.getChildren().add(listView);

		add(root, 0, 2, 1, 1);

		//TODO: Receive statements from facade

		submitButton = new Button("Submit");
		add(submitButton, 0, 11, 1, 1);
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	private class RadioListCell extends ListCell<String>{
		@Override
		public void updateItem(String obj, boolean empty) {
			super.updateItem(obj, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				RadioButton radioButton = new RadioButton(obj);
				radioButton.setToggleGroup(statementGroup);
				setGraphic(radioButton);
			}
		}
	}

	public void updateContents(String questionTitle) {
		questionField.setText(questionTitle);
		System.out.println(questionField.getText());
	}
}
