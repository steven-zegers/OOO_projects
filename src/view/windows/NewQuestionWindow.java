package view.windows;

import controller.NewQuestionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.Facade;
import view.panes.NewQuestionPane;

public class NewQuestionWindow extends Stage {

    private NewQuestionPane pane;
    private Stage stage;
    private Facade facade;

    public NewQuestionWindow(Stage stage, Facade facade) {
        setFacade(facade);
        this.setStage(stage);
        this.setPane(new NewQuestionPane(getFacade()));

        Scene mainScene = new Scene(this.getPane(), 500,300);
        this.getStage().setTitle("New Question");
        this.getStage().setScene(mainScene);
        this.setAlwaysOnTop(true);
        sizeToScene();
    }

    public NewQuestionPane getPane() {
        return pane;
    }

    public void setPane(NewQuestionPane pane) {
        this.pane = pane;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        this.getStage().show();
    }

    public void stop() {
        this.getStage().close();
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

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }
}
