package sample;

import entity.Product;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 * <b>Factory for creation custom {@link entity.Remnant remnant} cell for {@link ListView ListView}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantCellFactory implements Callback<ListView<Pair<Product, Integer>>, ListCell<Pair<Product, Integer>>> {

    /**
     * @param param {@link ListView List view} default value
     * @return {@link RemnantListViewCell remnant cell} with custom view and behaviour
     */
    @Override
    public ListCell<Pair<Product, Integer>> call(ListView<Pair<Product, Integer>> param) {
        return new RemnantListViewCell();
    }
}

