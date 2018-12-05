package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.Question;
import view.panes.EditQuestionPane;
import view.panes.NewQuestionPane;
import view.windows.EditQuestionWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EditQuestionController {
    EditQuestionWindow window;
    private Facade facade;
    private Question selectedQuestion;

    public EditQuestionController(Stage primaryStage, Facade facade, Question selectedQuestion) {
        this.setFacade(facade);
        this.setSelectedCategory(selectedQuestion);
        this.window = new EditQuestionWindow(primaryStage, facade);
        facade.addObserver(this.window.getPane());
        window.getPane().setQuestionTitle(selectedQuestion.getTitle());
        //window.getPane().setDescription(selectedQuestion.getDescription());
        window.getPane().setStatements(selectedQuestion.getStatements());
        window.getPane().setCategory(selectedQuestion.getCategoryTitle());
        window.getPane().setFeedback(selectedQuestion.getFeedback());
        facade.notifyObservers();
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setAddButtonHandler(new AddButtonHandler());
        this.window.setRemoveButtonHandler(new RemoveButtonHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Question getSelectedQuestion() {
        return this.selectedQuestion;
    }

    public void setSelectedCategory(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
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
            //todo: update category
            EditQuestionPane pane = window.getPane();
            String questionTitle = pane.getQuestionField().getText();
            String feedback = pane.getFeedbackField().getText();
            Category category = facade.getCategory((String) pane.getCategoryField().getValue());
            Question question = new Question(questionTitle, category, feedback);
            String[] allStatements = pane.getStatementsArea().getText().split("\n");
            List<String> statementsList = new ArrayList<>(Arrays.asList(allStatements));
            for (String statement : statementsList) {
                question.addStatement(statement);
            }
            facade.updateQuestion(question);
            window.stop();
        }
    }

    private class RemoveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                EditQuestionPane pane = window.getPane();
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

    private class AddButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                EditQuestionPane pane = window.getPane();
                String statement = pane.getStatementField().getText();
                pane.getStatementsArea().appendText(statement + "\n");
                pane.getStatementField().clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }
}
