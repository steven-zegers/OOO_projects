package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.panels.QuestionOverviewPane;
import view.windows.NewQuestionWindow;

import javax.swing.*;

public class NewQuestionController {

    NewQuestionWindow window;

    public NewQuestionController(Stage primaryStage) {
        this.window = new NewQuestionWindow(primaryStage);
        this.window.setAddButtonHandler(new AddButtonHandler());
        this.window.setRemoveButtonHandler(new RemoveButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.start();
    }

    private class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                System.out.println("Add");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

    private class RemoveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                System.out.println("Remove");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                System.out.println("Save");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

    private class CancelButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                System.out.println("Cancel");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

}
