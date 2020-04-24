package sample;

import entity.Product;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * <b>Factory for creation custom {@link Product product} cell for {@link ListView ListView}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class ProductCellFactory implements Callback<ListView<Product>, ListCell<Product>> {

    /**
     * @param param {@link ListView List view} default value
     * @return {@link ProductListViewCell product cell} with custom view and behaviour
     */
    @Override
    public ListCell<Product> call(ListView<Product> param) {
        return new ProductListViewCell();
    }
}
