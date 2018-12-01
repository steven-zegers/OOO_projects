package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.domain.Facade;
import sun.plugin2.message.Message;
import view.panes.MessagePane;
import view.panes.TestPane;
import view.windows.TestWindow;

import javax.swing.*;

public class TestController
{
    private TestWindow window;
    private Facade facade;
    private MessagePane pane;

    public TestController(Facade facade) {
        setFacade(facade);
    }

    public void setPane(MessagePane pane) {
        this.pane = pane;
        this.setup();
    }

    public void setup() {
        this.pane.setNewAction(new EvaluateButtonHandler());
        this.getFacade().addObserver(this.pane);
    }

    public MessagePane getPane() {
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
                System.out.print(getPane().getScores());
                new NewTestController(new Stage(), getFacade());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }
}
