package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.TestOverviewPane;
import view.windows.TestWindow;

import javax.swing.*;

public class TestOverviewController
{
    private Facade facade;
    private TestOverviewPane pane;

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

    private class EvaluateButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                new TestController(new Stage(), getFacade());
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
            }
        }
    }
}
