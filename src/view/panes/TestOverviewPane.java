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

public class TestOverviewPane extends GridPane implements Observer{
	private Button testButton;
	private Facade facade;
	private Label scoreField;
	private Label feedbackField;
	private Label finishedField;

	public TestOverviewPane(Facade facade){
		this.setFacade(facade);
		setBorder(new Border(new BorderStroke(Color.BLACK,
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		scoreField = new Label("");

		add(scoreField, 0, 0, 1, 1);
		setHalignment(scoreField, HPos.CENTER);

		feedbackField = new Label("");

		add(feedbackField,0,1,1,1);
		setHalignment(feedbackField, HPos.CENTER);

		String finished = null;

		if(facade.isFinishedBefore())
		{
			finished = "You have finished this test before! Previous score: \n" + facade.readOldScore() + "\nWould you like to retake it?";
		}
		else
		{
			finished = "You have never finished this evaluation before.";
		}

		scoreField.setText(finished);

		testButton = new Button("Evaluate");
		if (facade.isFinishedBefore()) {
			testButton.setText("Retake test");
		}

		add(testButton, 0,3,1,1);
		setHalignment(testButton, HPos.CENTER);
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
		if (facade.getCurrentTest().isFinished()) {
			if(facade.getFeedbackType().equals("score")) {
				scoreField.setText(facade.getScoreString());
			} else if (facade.getFeedbackType().equals("feedback")) {
				scoreField.setText("");
				if(facade.isItAPerfectTest()) {
					feedbackField.setText("Schitterend! Alles perfect!");
				} else {
					feedbackField.setText(facade.getFullFeedback());
				}

			}
		} else {
			if (facade.isFinishedBefore()) {
				scoreField.setText(facade.readOldScore());
			}
		}


	}
}
