package view.panes;

import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.domain.Facade;
import model.domain.Question;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Facade facade;
	private ObservableList<String> statements;
	private List<RadioButton> radioButtons;

	public TestPane (Facade facade){
		setFacade(facade);
		this.setPrefHeight(300);
		this.setPrefWidth(750);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label(facade.getTitleOfCurrentQuestionOfCurrentTest());

		add(questionField, 0, 0, 1, 1);

		List<String> statementList = facade.getStatementsOfCurrentQuestion();
		List<String> shuffledAnswers = new ArrayList<>(statementList);
		Collections.shuffle(shuffledAnswers);
		statements = FXCollections.observableArrayList(shuffledAnswers);
		statementGroup = new ToggleGroup();
		VBox box = new VBox();
		radioButtons = new ArrayList<>();
		for (String statement : statements) {
			RadioButton radioButton = new RadioButton(statement);
			radioButtons.add(radioButton);
			radioButton.setToggleGroup(statementGroup);
			box.getChildren().add(radioButton);
		}
		radioButtons.get(0).setSelected(true);
		add(box, 0, 2, 1, 1);

		submitButton = new Button("Submit");
		add(submitButton, 0, 11, 1, 1);
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public Button getSubmitButton() {
		return this.submitButton;
	}

	public String getSelectedStatements() {
		if(statementGroup.getSelectedToggle()!=null) {
			RadioButton selectedRadioButton = (RadioButton) statementGroup.getSelectedToggle();
			return selectedRadioButton.getText();
		} else {
			throw new IllegalArgumentException("Je moet een antwoord selecteren");
		}
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

}
