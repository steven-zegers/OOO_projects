package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.EditCategoryPane;

public class EditCategoryWindow extends Stage{

    private EditCategoryPane pane;
    private Stage primaryStage;
    private Facade facade;

    public EditCategoryWindow(Stage primaryStage, Facade facade) {
        this.setFacade(facade);

        this.setStage(primaryStage);
        this.setPane(new EditCategoryPane(facade));

        Scene mainScene = new Scene(this.getPane(), 700,150);
        this.getStage().setTitle("Edit category");
        this.getStage().setScene(mainScene);
        sizeToScene();
    }

    public void setPane(EditCategoryPane categoryDetailPane) {
        this.pane = categoryDetailPane;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public EditCategoryPane getPane() {
        return this.pane;
    }

    public Stage getStage() {
        return this.primaryStage;
    }

    public void setSaveButtonHandler(EventHandler<ActionEvent> saveButtonHandler) {
        this.getPane().setSaveButtonHandler(saveButtonHandler);
    }

    public void setCancelButtonHandler(EventHandler<ActionEvent> cancelButtonHandler) {
        this.getPane().setCancelButtonHandler(cancelButtonHandler);
    }

    public void start() {
        this.getStage().show();
    }

    public void stop() {
        this.getStage().close();
    }

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }
}
