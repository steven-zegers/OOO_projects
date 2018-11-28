package view.windows;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.Facade;
import model.domain.Question;
import view.panes.TestPane;

import java.util.List;

public class TestWindow extends Stage
{
    private Stage stage;
    private TestPane pane;

    public TestWindow(Stage stage)
    {
        this.setStage(stage);
        this.setPane(new TestPane());

        Scene mainScene = new Scene(this.getPane(), 500,300);
        this.getStage().setTitle("Test");
        this.getStage().setScene(mainScene);
        this.setAlwaysOnTop(true);
        sizeToScene();
    }

    public TestPane getPane() {
        return this.pane;
    }

    public Stage getStage() {
        return this.stage;
}

    public void start() {
        this.getStage().show();
    }

    public void stop() {
        this.getStage().close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPane(TestPane pane) {
        this.pane = pane;
    }

    public void setProcessAnswerAction(EventHandler<ActionEvent> submitHandler)
    {
        this.getPane().setProcessAnswerAction(submitHandler);
    }
}
