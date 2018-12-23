package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.*;
import view.windows.TestWindow;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class TestController {
    private Facade facade;
    private TestWindow window;
    private Evaluation evaluation;

    public TestController(Stage stage, Facade facade, Evaluation evaluation) {
        setFacade(facade);
        Test test = new Test(facade);
        facade.setCurrentTest(test);
        setWindow(new TestWindow(stage, facade));
        facade.addObserver(getWindow().getPane());
		setEvaluation(evaluation);
        this.window.setProcessAnswerAction(new ProcessAnswerHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
        
    }
    public TestWindow getWindow() {
        return this.window;
    }

    public void setWindow(TestWindow window) {
        this.window = window;
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

    private class ProcessAnswerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (!facade.getCurrentTest().isFinished()) {
                if (facade.getStatementsOfCurrentQuestion().get(0).equals(getWindow().getPane().getSelectedStatements())) {
                    facade.handleCorrectAnswer();
                    String categoryOfQuestion = facade.getCurrentQuestionCategoryTitle();
                    facade.handleQuestionOfCategoryCorrect(categoryOfQuestion);
                } else {
                    facade.handleIncorrectAnswer(facade.getCurrentQuestion());
                }
                if (facade.getCurrentTest().canAdvance()) {
                    facade.advanceCurrentTest();
                    facade.notifyObservers();
                } else {
                    facade.setCurrentTestFinished();
                    getWindow().stop();
                }
            } else {
                getWindow().stop();
            }

        }
    }
}
