package controller;

import javafx.scene.control.Alert;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class ControllerHelper {
    public static void showErrorMessage(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("You caused a " + e.getClass());
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
