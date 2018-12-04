package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Observer;
import view.panes.CategoryOverviewPane;

import javax.swing.*;

public class CategoryOverviewController {

	private CategoryOverviewPane pane;
	private Facade facade;

	public CategoryOverviewController(Facade facade) {
		this.setFacade(facade);
	}

	public void setPane(CategoryOverviewPane pane) {
		this.pane = pane;
		this.setup();
	}

	public void setup() {
		this.pane.setNewAction(new NewButtonHandler());
		this.pane.setEditAction(new EditCategoryHandler());
		this.getFacade().addObserver(this.pane);
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
				new NewCategoryController(new Stage(), getFacade());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
				e.printStackTrace();
			}
		}
	}

	private class EditCategoryHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			if(event.getClickCount()==2) {
				try{
					new EditCategoryController(new Stage(), getFacade());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
					e.printStackTrace();
				}
			}
		}
	}
}