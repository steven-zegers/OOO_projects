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
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class EditCategoryController {
    private NewCategoryWindow window;
    private Facade facade;
    private Category selectedCategory;
    private String oldTitle;

    public EditCategoryController(Stage primaryStage, Facade facade, Category selectedCategory) {
        this.setFacade(facade);
        this.setSelectedCategory(selectedCategory);
        this.setOldTitle(selectedCategory.getTitle());
        this.window = new NewCategoryWindow(primaryStage, facade);
        facade.addObserver(this.window.getPane());
        window.getPane().setTitle(selectedCategory.getTitle());
        window.getPane().setDescription(selectedCategory.getDescription());
        if (selectedCategory instanceof SubCategory) {
            window.getPane().setMainCategoryTitle(((SubCategory)selectedCategory).getSuperCategory().getTitle());
        }

        facade.notifyObservers();
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.getStage().setAlwaysOnTop(true);
        this.window.start();
    }

    private void setOldTitle(String title) {
        this.oldTitle = title;
    }

    public String getOldTitle() {
        return this.oldTitle;
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    private class CancelButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
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
        public void handle(ActionEvent event) {
            try {
                NewCategoryPane pane = window.getPane();
                String title = pane.getTitleField().getText();
                if (title.contains(":")) throw new IllegalArgumentException("Please do not use any ':' in your question.");
                String description = pane.getDescriptionField().getText();
                if (description.contains(":")) throw new IllegalArgumentException("Please do not use any ':' in your question.");
                if (pane.getCategoryField().getValue() != null) {
                    Category superCategory = facade.getCategory((String) pane.getCategoryField().getValue());
                    SubCategory newCategory = new SubCategory(title, description, superCategory);
                    facade.updateCategory(getOldTitle(), newCategory);
                } else {
                    Category newCategory = new Category(title, description);
                    facade.updateCategory(getOldTitle(), newCategory);
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
