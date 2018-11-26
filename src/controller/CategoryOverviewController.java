package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.CategoryOverviewPane;

import javax.swing.*;

public class CategoryOverviewController {

	CategoryOverviewPane pane;
	private Facade facade;

	public CategoryOverviewController(Facade facade) {
		setFacade(facade);
	}

	public void setPane(CategoryOverviewPane pane) {
		this.pane = pane;
		this.setup();
	}

	public void setup() {
		this.pane.setNewAction(new NewButtonHandler());
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	private class NewButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public  void handle(ActionEvent arg0) {
			try {
				new NewCategoryController(new Stage(), facade);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
				e.printStackTrace();
			}
		}
	}

}