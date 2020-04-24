package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * <b>Model to define Remnant</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">2.0</span>
 */
public class Remnant implements Serializable {
    /**
     * key to check serialization
     */
    private static final long serialVersionUID = 12345678L;
    /**
     * product id - unique combination with warehouse id
     */
    private int productId;
    /**
     * warehouse id  - unique combination with product id
     */
    private int warehouseId;
    /**
     * remnant amount
     */
    private int amount;

    /**
     * Construct with all params
     * @param productId {@link Product product} id
     * @param warehouseId {@link Warehouse warehouse} id
     * @param amount remnant amount
     */
    public Remnant(int productId, int warehouseId, int amount) {
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.amount = amount;
    }

    /**
     * getter for {@link Product product} id
     * @return {@link Product product} id of remnant
     */
    public int getProductId() {
        return productId;
    }

    /**
     * setter for {@link Product product} id
     * @param productId new {@link Product product} id of remnant
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * getter for {@link Warehouse warehouse} id
     * @return {@link Warehouse warehouse} id of remnant
     */
    public int getWarehouseId() {
        return warehouseId;
    }

    /**
     * setter for {@link Warehouse warehouse} id
     * @param warehouseId new {@link Warehouse warehouse} id of remnant
     */
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * getter for amount
     * @return remnant amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter for amount
     * @param amount new remnant amoubt
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o remnant to compare
     * @return true if remnants are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remnant remnant = (Remnant) o;
        return getProductId() == remnant.getProductId() &&
                getWarehouseId() == remnant.getWarehouseId() &&
                getAmount() == remnant.getAmount();
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getWarehouseId(), getAmount());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "Remnant{" +
                "productId=" + productId +
                ", warehouseId=" + warehouseId +
                ", amount=" + amount +
                '}';
    }
}
