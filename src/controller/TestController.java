package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Question;
import model.domain.Test;
import view.windows.TestWindow;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class TestController {
    private Facade facade;
    private TestWindow window;

    public TestController(Stage stage, Facade facade) {
        setFacade(facade);
        Test test = new Test(facade);
        facade.setCurrentTest(test);
        setWindow(new TestWindow(stage, facade));
        facade.addObserver(getWindow().getPane());
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
                    //setWindow(new TestWindow(getWindow().getStage(), facade));
                    //getWindow().setProcessAnswerAction(new ProcessAnswerHandler());
                    getWindow().getPane().update();
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
