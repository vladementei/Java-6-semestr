package by.dementei.entity;

import java.io.Serializable;
import java.util.Objects;

public class Remnant implements Serializable {
    private static final long serialVersionUID = 12344321L;
    private int productId;
    private int warehouseId;
    private int amount;

    public Remnant() {
        this.productId = 0;
        this.warehouseId = 0;
        this.amount = 0;
    }

    public Remnant(int productId, int warehouseId, int amount) {
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remnant remnant = (Remnant) o;
        return getProductId() == remnant.getProductId() &&
                getWarehouseId() == remnant.getWarehouseId() &&
                getAmount() == remnant.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getWarehouseId(), getAmount());
    }

    @Override
    public String toString() {
        return "Remnant{" +
                "productId=" + productId +
                ", warehouseId=" + warehouseId +
                ", amount=" + amount +
                '}';
    }
}
