package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * <b>Factory for basic dialogs</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class Dialogs {

    /**
     * create and show error dialog
     * @param contentText error message
     */
    public static void showErrorDialog(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText, ButtonType.CLOSE);
        alert.showAndWait();
    }

    /**
     * create and show confirm dialog
     * @param contentText confirm message
     */
    public static void showConfirmDialog(String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText, ButtonType.CLOSE);
        alert.showAndWait();
    }
}
