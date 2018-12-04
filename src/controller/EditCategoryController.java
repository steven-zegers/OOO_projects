package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.SubCategory;

import javax.swing.*;

public class EditCategoryController {
    EditCategoryWindow window;
    private Facade facade;
    private Category selectedCategory;

    public EditCategoryController(Stage primaryStage, Facade facade, Category selectedCategory) {
        this.setFacade(facade);
        this.setSelectedCategory(selectedCategory);
        this.window = new EditCategoryWindow(primaryStage, facade);
        facade.addObserver(this.window.getPane());
        window.getPane().setTitle(selectedCategory.getTitle());
        window.getPane().setDescription(selectedCategory.getDescription());
        if (selectedCategory instanceof SubCategory) {
            window.getPane().setMainCategoryTitle(((SubCategory)selectedCategory).getSuperCategory().getTitle());
        }

        facade.notifyObservers();
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
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
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);

            }
        }
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //todo: update category

            window.stop();
        }
    }
}
