package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.db.QuestionDB;
import model.domain.Question;
import view.panes.QuestionOverviewPane;

import javax.swing.*;

public class QuestionOverviewController {
    private QuestionOverviewPane pane;


    public QuestionOverviewController(Stage primaryStage) {

    }

    public void setPane(QuestionOverviewPane pane) {
        this.pane = pane;
        this.setup();
    }

    public void setup() {
        this.pane.setNewAction(new NewButtonHandler());
    }

    private class NewButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                new NewQuestionController(new Stage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

}
