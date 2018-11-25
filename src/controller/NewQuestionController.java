package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.db.QuestionDB;
import model.domain.Category;
import model.domain.Question;
import view.panes.NewQuestionPane;
import view.windows.NewQuestionWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewQuestionController {

    NewQuestionWindow window;

    public NewQuestionController(Stage primaryStage) {
        this.window = new NewQuestionWindow(primaryStage);
        this.window.setAddButtonHandler(new AddButtonHandler());
        this.window.setRemoveButtonHandler(new RemoveButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
    }

    private class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                NewQuestionPane pane = window.getPane();
                String statement = pane.getStatementField().getText();
                pane.getStatementsArea().appendText(statement + "\n");
                pane.getStatementField().clear();
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
                NewQuestionPane pane = window.getPane();
                String statementToDelete = pane.getStatementField().getText();
                String[] allStatements = pane.getStatementsArea().getText().split("\n");

                List<String> statementsList = new LinkedList<>(Arrays.asList(allStatements));
                statementsList.remove(statementToDelete);

                pane.getStatementsArea().clear();
                for (String statement : statementsList) {
                    pane.getStatementsArea().appendText(statement + "\n");
                }
                pane.getStatementField().clear();
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
                NewQuestionPane pane = window.getPane();
                CategoryDB categoryDB = new CategoryDB();
                QuestionDB questionDB = new QuestionDB();
                String questionTitle = pane.getQuestionField().getText();
                String feedback = pane.getFeedbackField().getText();
                Category category = categoryDB.getCategory((String) pane.getCategoryField().getValue());
                Question question = new Question(questionTitle, category, feedback);
                String[] allStatements = pane.getStatementsArea().getText().split("\n");
                List<String> statementsList = new ArrayList<>(Arrays.asList(allStatements));
                for (String statement : statementsList) {
                    question.addStatement(statement);
                }
                questionDB.addQuestion(question);
                window.stop();
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
                window.stop();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

}
