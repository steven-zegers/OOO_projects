package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.Question;
import view.panes.QuestionOverviewPane;

import javax.swing.*;

public class QuestionOverviewController {

    private QuestionOverviewPane pane;
    private Facade facade;

    public QuestionOverviewController(Facade facade) {
		this.setFacade(facade);
    }
    public Facade getFacade() {
        return facade;
    }
    public void setPane(QuestionOverviewPane pane) {
        this.pane = pane;
        this.setup();
    }

    public void setup() {
        this.pane.setNewAction(new NewButtonHandler());
        this.pane.setEditAction(new EditQuestionHandler());
        this.getFacade().addObserver(this.pane);
    }

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

    private class NewButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                new NewQuestionController(new Stage(), getFacade());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

    private class EditQuestionHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if(event.getClickCount()==2) {
                try {
                    Question selectedQuestion = (Question) pane.getTable().getSelectionModel().getSelectedItem();
                    new EditQuestionController(new Stage(), getFacade(), selectedQuestion);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                    e.printStackTrace();
                }
            }
        }
    }

}
