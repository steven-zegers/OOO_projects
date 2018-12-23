package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.Question;
import view.panes.NewQuestionPane;
import view.windows.NewQuestionWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class NewQuestionController {

    NewQuestionWindow window;
    private Facade facade;

    public NewQuestionController(Stage primaryStage, Facade facade) {
        this.setFacade(facade);
        this.window = new NewQuestionWindow(primaryStage, facade);
        this.window.setAddButtonHandler(new AddButtonHandler());
        this.window.setRemoveButtonHandler(new RemoveButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.getStage().setAlwaysOnTop(true);
        this.window.start();
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    private class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                NewQuestionPane pane = window.getPane();
                String statement = pane.getStatementField().getText();
                if (statement.contains(":") || statement.contains(";") || statement.isEmpty()) throw new IllegalArgumentException("Please do not use any ':' or ';' in your statements.");
                pane.getStatementsArea().appendText(statement + "\n");
                pane.getStatementField().clear();
            } catch (Exception e) {
                window.getStage().setAlwaysOnTop(false);
                //JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
                window.getStage().setAlwaysOnTop(true);
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
                window.getStage().setAlwaysOnTop(false);
                //JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
                window.getStage().setAlwaysOnTop(true);
            }
        }
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                NewQuestionPane pane = window.getPane();
                String questionTitle = pane.getQuestionField().getText();
                String feedback = pane.getFeedbackField().getText();
                Category category = facade.getCategory((String) pane.getCategoryField().getValue());
                Question question = new Question(questionTitle, category, feedback);
                String[] allStatements = pane.getStatementsArea().getText().split("\n");
                if(allStatements.length < 2) throw new IllegalArgumentException("You need atleast 2 answers to your question!");
                List<String> statementsList = new ArrayList<>(Arrays.asList(allStatements));
                for (String statement : statementsList) {
                    question.addStatement(statement);
                }
                facade.addQuestion(question);
                window.stop();
            } catch (Exception e) {
                window.getStage().setAlwaysOnTop(false);
                //JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
                window.getStage().setAlwaysOnTop(true);
            }
        }
    }

    private class CancelButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                window.stop();
            } catch (Exception e) {
                window.getStage().setAlwaysOnTop(false);
                //JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
                window.getStage().setAlwaysOnTop(true);
            }
        }
    }

}
