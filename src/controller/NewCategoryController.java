package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.db.CategoryDB;
import model.domain.Category;
import model.domain.SubCategory;
import view.panes.NewCategoryPane;
import view.windows.NewCategoryWindow;

import javax.swing.*;

public class NewCategoryController {

    NewCategoryWindow window;

    public NewCategoryController(Stage primaryStage) {
        this.window = new NewCategoryWindow(primaryStage);
        this.window.setCancelButtonHandler(new CancelButtonHandler());
        this.window.setSaveButtonHandler(new SaveButtonHandler());
        this.window.setAlwaysOnTop(true);
        this.window.start();
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

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public  void handle(ActionEvent arg0) {
            try {
                NewCategoryPane pane = window.getPane();
                CategoryDB db = new CategoryDB();
                String title = pane.getTitleField().getText();
                String description = pane.getDescriptionField().getText();
                if (pane.getCategoryField().getValue() != null) {
                    Category superCategory = db.getCategory((String) pane.getCategoryField().getValue());
                    SubCategory newSubCategory = new SubCategory(title, description, superCategory);
                    db.addCategory(newSubCategory);
                } else {
                    Category newCategory = new Category(title, description);
                    db.addCategory(newCategory);
                }
                window.stop();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(), 0);
                e.printStackTrace();
            }
        }
    }

}