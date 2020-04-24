package sample;

import entity.Warehouse;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * <b>Factory for creation custom {@link Warehouse warehouse} cell for {@link ListView ListView}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class WarehouseCellFactory implements Callback<ListView<Warehouse>, ListCell<Warehouse>> {

    /**
     * @param param {@link ListView List view} default value
     * @return {@link WarehouseListViewCell warehouse cell} with custom view and behaviour
     */
    @Override
    public ListCell<Warehouse> call(ListView<Warehouse> param) {
        return new WarehouseListViewCell();
    }
}
