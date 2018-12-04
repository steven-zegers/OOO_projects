package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import view.windows.NewCategoryWindow;

import javax.swing.*;

public class EditCategoryController {
    EditCategoryWindow window;
    private Facade facade;

    public EditCategoryController(Stage primaryStage, Facade facade) {
        this.setFacade(facade);
        this.window = new EditCategoryWindow(primaryStage, facade);
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    private class CancelButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                window.stop();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);

            }
        }
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

        }
    }
}
