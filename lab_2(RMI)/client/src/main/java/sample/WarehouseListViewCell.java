package sample;

import entity.Warehouse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class WarehouseListViewCell extends ListCell<Warehouse> {

    @FXML
    private Label id;

    @FXML
    private Label title;

    @FXML
    private Label address;

    public WarehouseListViewCell() {
        loadFXML();
    }

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
