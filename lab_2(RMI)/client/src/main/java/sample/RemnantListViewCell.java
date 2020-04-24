package sample;

import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.util.Pair;

import java.io.IOException;

/**
 * <b>Class to define custom {@link entity.Remnant remannt} cell</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantListViewCell extends ListCell<Pair<Product, Integer>> {

    /**
     * {@link Product product} name of selected {@link entity.Remnant remnant }
     */
    @FXML
    private Label productName;

    /**
     * {@link Product product} amount of selected {@link entity.Remnant remnant }
     */
    @FXML
    private Label amount;

    /**
     * Default constructor
     */
    public RemnantListViewCell() {
        loadFXML();
    }

    /**
     * Set view of {@link entity.Remnant remnant} cell
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("remnant_info_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Binds view with {@link entity.Remnant remnant}variable
     * @param remnant current {@link entity.Remnant remnant} cell model
     * @param empty if current {@link entity.Remnant remnant} cell is empty
     */
    @Override
    protected void updateItem(Pair<Product, Integer> remnant, boolean empty) {
        super.updateItem(remnant, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            productName.setText(remnant.getKey().getName());
            amount.setText(String.valueOf(remnant.getValue()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

