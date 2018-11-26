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

    public TestWindow(Stage stage, Facade facade)
    {
        this.setStage(stage);
        this.setPane(new TestPane());

        setSubmitHandler(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

            }
        });

        Scene mainScene = new Scene(pane, 500,300);
        this.stage.setTitle("Test");
        this.stage.setScene(mainScene);
        this.setAlwaysOnTop(true);
        sizeToScene();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPane(TestPane pane) {
        this.pane = pane;
    }

    public void setSubmitHandler(EventHandler<ActionEvent> submitHandler)
    {
        this.pane.setProcessAnswerAction(submitHandler);
    }
}
