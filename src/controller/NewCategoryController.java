package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.SubCategory;
import view.panes.NewCategoryPane;
import view.windows.NewCategoryWindow;

import javax.swing.*;

/**
 * @author Steven Zegers
 * @author Wout De Boeck
 * @author Thibault Stroobants
 */
public class NewCategoryController {

    NewCategoryWindow window;
    private Facade facade;

    public NewCategoryController(Stage primaryStage, Facade facade) {
        this.setFacade(facade);
        this.window = new NewCategoryWindow(primaryStage, facade);
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.getStage().setAlwaysOnTop(true);
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
        public  void handle(ActionEvent arg0) {
            try {
                window.stop();
            } catch (Exception e) {
                window.getStage().setAlwaysOnTop(false);
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
                NewCategoryPane pane = window.getPane();
                String title = pane.getTitleField().getText();
                String description = pane.getDescriptionField().getText();
                if (pane.getCategoryField().getValue() != null) {
                    Category superCategory = facade.getCategory((String) pane.getCategoryField().getValue());
                    SubCategory newSubCategory = new SubCategory(title, description, superCategory);
                    facade.addCategory(newSubCategory);
                } else {
                    Category newCategory = new Category(title, description);
                    facade.addCategory(newCategory);
                }
                window.stop();
            } catch (Exception e) {
                window.getStage().setAlwaysOnTop(false);
                ControllerHelper.showErrorMessage(e);
                e.printStackTrace();
                window.getStage().setAlwaysOnTop(true);
            }
        }
    }

}
