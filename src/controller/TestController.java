package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.panes.TestPane;
import view.windows.TestWindow;

public class TestController
{
    private TestWindow window;

    public TestController(Stage primaryStage)
    {
        this.window = new TestWindow(primaryStage);

    }
}
