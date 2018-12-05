package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.EditQuestionPane;

public class EditQuestionWindow extends Stage {
    private EditQuestionPane pane;
    private Stage primaryStage;
    private Facade facade;

    public EditQuestionWindow(Stage primaryStage, Facade facade) {
        this.setFacade(facade);

        this.setStage(primaryStage);
        this.setPane(new EditQuestionPane(facade));

        Scene mainScene = new Scene(this.getPane(), 500,300);
        this.getStage().setTitle("Edit question");
        this.getStage().setScene(mainScene);
        sizeToScene();
    }

    public void setPane(EditQuestionPane questionPane) {
        this.pane = questionPane;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public EditQuestionPane getPane() {
        return this.pane;
    }

    public Stage getStage() {
        return this.primaryStage;
    }

    public void setAddButtonHandler(EventHandler<ActionEvent> addButtonHandler) {
        this.getPane().setAddButtonHandler(addButtonHandler);
    }

    public void setRemoveButtonHandler(EventHandler<ActionEvent> removeButtonHandler) {
        this.getPane().setRemoveButtonHandler(removeButtonHandler);
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

