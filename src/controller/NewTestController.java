package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Test;
import view.panes.TestPane;
import view.windows.TestWindow;

public class NewTestController {
    private TestPane pane;
    private Facade facade;
    private TestWindow window;

    public NewTestController(Stage stage, Facade facade) {
        setWindow(new TestWindow(stage, facade));
        setFacade(facade);
        Test test;
        test = new Test(facade);
        facade.setCurrentTest(test);
        this.pane = new TestPane(getFacade());
        this.window.setProcessAnswerAction(new ProcessAnswerHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
        
    }
    public TestWindow getWindow() {
        return this.window;
    }
    public void setPane(TestPane pane) {
        this.pane = pane;
    }
    public TestPane getPane() {
        return this.pane;
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
            facade.advanceCurrentTest();
        }
    }
}
