package view.panes;

import controller.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.domain.Category;
import model.domain.Facade;
import model.domain.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven Zegers
 */
public class SettingsPane extends GridPane {
    private List<RadioButton> radioButtons;
    private ToggleGroup feedbackGroup;
    private Button submitButton;
    private Label label = new Label();
    private Facade facade;

    public SettingsPane(Facade facade) {
        this.facade = facade;
        this.setPadding(new Insets(5, 5, 5, 10));
        this.setVgap(5);
        this.setHgap(10);
        VBox box = new VBox();
        label.setText("Which kind of feedback would you like?");
        radioButtons = new ArrayList<>();
        feedbackGroup = new ToggleGroup();
        for (String feedbackType : facade.getEvaluationTypes()) {
            RadioButton radioButton = new RadioButton(feedbackType);
            radioButtons.add(radioButton);
            radioButton.setToggleGroup(feedbackGroup);
            box.getChildren().add(radioButton);
        }
        for (RadioButton button : radioButtons) {
            if (button.getText().toLowerCase().equals(facade.getEvaluationType())) {
                button.setSelected(true);
            }
        }
        add(box, 0, 1, 1, 1);
        add(label, 0, 0, 1, 1);
        submitButton = new Button("Save");
        setSaveButtonHandler(new UpdateEvaluationType());
        add(submitButton, 0, 3, 1, 1);
    }

    public Facade getFacade() {
        return this.facade;
    }
    public void setSaveButtonHandler(EventHandler<ActionEvent> updateEvaluationType) {
        submitButton.setOnAction(updateEvaluationType);
    }
    private class UpdateEvaluationType implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            if (!getSelectedStatement().toLowerCase().equals(facade.getEvaluationType())) {
                facade.updateEvaluationType(getSelectedStatement().toLowerCase());
            }
        }
    }

    public String getSelectedStatement() {
        if(feedbackGroup.getSelectedToggle()!=null) {
            RadioButton selectedRadioButton = (RadioButton) feedbackGroup.getSelectedToggle();
            return selectedRadioButton.getText();
        } else {
            throw new IllegalArgumentException("Je moet een antwoord selecteren");
        }
    }
}
