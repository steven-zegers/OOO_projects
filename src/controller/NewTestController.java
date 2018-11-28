package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.TestPane;
import view.windows.TestWindow;

public class NewTestController {
    TestPane pane;
    private Facade facade;
    private TestWindow window;

    public NewTestController(Stage stage, Facade facade) {
        setWindow(new TestWindow(stage));
        setFacade(facade);
        this.pane = new TestPane();
        this.window.setProcessAnswerAction(new ProcessAnswerHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
        
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

        }
    }
}
