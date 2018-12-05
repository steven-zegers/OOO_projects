package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Facade;
import model.domain.SubCategory;
import view.panes.NewCategoryPane;
import view.windows.NewCategoryWindow;

import javax.swing.*;

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
        this.window.setAlwaysOnTop(true);
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
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);

            }
        }
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //todo: update category
            NewCategoryPane pane = window.getPane();
            String title = pane.getTitleField().getText();
            String description = pane.getDescriptionField().getText();
            if (pane.getCategoryField().getValue() != null) {
                Category superCategory = facade.getCategory((String) pane.getCategoryField().getValue());
                SubCategory newCategory = new SubCategory(title, description, superCategory);
                facade.updateCategory(getOldTitle(), newCategory);
            } else {
                Category newCategory = new Category(title, description);
                facade.updateCategory(getOldTitle(), newCategory);
            }
            window.stop();
        }
    }
}
