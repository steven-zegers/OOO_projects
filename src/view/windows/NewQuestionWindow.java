package view.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panels.NewQuestionPane;

public class NewQuestionWindow extends Stage {

    private NewQuestionPane pane;
    private Stage stage;

    public NewQuestionWindow(Stage stage) {
        this.setStage(stage);
        this.setPane(new NewQuestionPane());

        Scene mainScene = new Scene(this.getPane(), 500,300);
        this.getStage().setTitle("New Question");
        this.getStage().setScene(mainScene);
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

}
