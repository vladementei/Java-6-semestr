package sample;

import entity.Warehouse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

/**
 * <b>Class to define custom {@link Warehouse warehouse} cell</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class WarehouseListViewCell extends ListCell<Warehouse> {

    /**
     * {@link Warehouse warehouse} id
     */
    @FXML
    private Label id;

    /**
     * {@link Warehouse warehouse} title
     */
    @FXML
    private Label title;

    /**
     * {@link Warehouse warehouse} address
     */
    @FXML
    private Label address;

    /**
     * Default constructor
     */
    public WarehouseListViewCell() {
        loadFXML();
    }

    /**
     * Set view of {@link Warehouse warehouse} cell
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("warehouse_info_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Binds view with {@link Warehouse warehouse} variable
     * @param warehouse current {@link Warehouse warehouse} cell model
     * @param empty if current {@link Warehouse warehouse} cell is empty
     */
    @Override
    protected void updateItem(Warehouse warehouse, boolean empty) {
        super.updateItem(warehouse, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            id.setText(String.valueOf(warehouse.getId()));
            title.setText(warehouse.getTitle());
            address.setText(warehouse.getLocation());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
