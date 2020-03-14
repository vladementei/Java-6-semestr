package sample;

import entity.Warehouse;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class WarehouseCellFactory implements Callback<ListView<Warehouse>, ListCell<Warehouse>> {

    @Override
    public ListCell<Warehouse> call(ListView<Warehouse> param) {
        return new WarehouseListViewCell();
    }
}
