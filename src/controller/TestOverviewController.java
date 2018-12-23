package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Evaluation;
import model.domain.Facade;
import model.domain.FeedbackEvaluation;
import model.domain.ScoreEvaluation;
import view.panes.TestOverviewPane;
import view.windows.TestWindow;

import javax.swing.*;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class TestOverviewController
{
    private Facade facade;
    private TestOverviewPane pane;
    private Evaluation evaluation;

    public TestOverviewController(Facade facade) {
        setFacade(facade);
    }

    public void setPane(TestOverviewPane pane) {
        this.pane = pane;
        this.setup();
    }

    public void setup() {
        this.pane.setNewAction(new EvaluateButtonHandler());
        this.getFacade().addObserver(this.pane);
    }

    public TestOverviewPane getPane() {
        return this.pane;
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    private class EvaluateButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                new TestController(new Stage(), getFacade(), evaluation);
            } catch (Exception e) {
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
            }
        }
    }
}
