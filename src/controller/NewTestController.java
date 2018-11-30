package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Test;
import view.windows.TestWindow;

public class NewTestController {
    private Facade facade;
    private TestWindow window;

    public NewTestController(Stage stage, Facade facade) {
        setWindow(new TestWindow(stage, facade));
        setFacade(facade);
        this.window.setProcessAnswerAction(new ProcessAnswerHandler());
        Test test;
        test = new Test(facade);
        facade.setCurrentTest(test);
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
            if (facade.getStatementsOfCurrentQuestion().get(0).equals(getWindow().getPane().getSelectedStatements())) {
                System.out.println("Correct geantwoord!");
                facade.handleCorrectAnswer();
            }
            if (facade.getCurrentTest().canAdvance()) {
                facade.advanceCurrentTest();
                setWindow(new TestWindow(getWindow().getStage(), facade));
                getWindow().setProcessAnswerAction(new ProcessAnswerHandler());
            } else {
                getWindow().close();
                System.out.println(facade.getCurrentTest().getScore());
            }
        }
    }
}
