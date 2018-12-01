package view.panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.domain.Facade;
import model.domain.Observer;

public class MessagePane extends GridPane implements Observer{
	private Button testButton;
	private Facade facade;
	private Label scoreField;
	private String scores;

	public MessagePane (Facade facade){
		this.setFacade(facade);
		setBorder(new Border(new BorderStroke(Color.BLACK,
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


        scoreField = new Label("");
		/*scores = "";
		String totaleScore = Integer.toString(facade.getCurrentTest().getScore());
		scores += "Your score: " + totaleScore + "\n";
		for (String categoryTitle : facade.getCategoryTitles()) {
			scores += "Category " + categoryTitle + ": " + Integer.toString(facade.getScoreOfCategory(categoryTitle)) + "\n";
		}
		scoreField.setText(scores);*/
		add(scoreField, 0, 0, 1, 1);
		setHalignment(scoreField, HPos.CENTER);

		testButton = new Button("Evaluate");

		add(testButton, 0,1,1,1);
		setHalignment(testButton, HPos.CENTER);
	}

	public String getScores() {
		return scores;
	}

	public void setNewAction(EventHandler<ActionEvent> newAction) {
		testButton.setOnAction(newAction);
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	@Override
	public void update() {
		scores = "";
		String totaleScore = Integer.toString(facade.getCurrentTest().getScore());
		scores += "Your score: " + totaleScore + "\n";
		for (String categoryTitle : facade.getCategoryTitles()) {
			scores += "Category " + categoryTitle + ": " + Integer.toString(facade.getScoreOfCategory(categoryTitle)) + "\n";
		}
		scoreField.setText(scores);
	}
}
