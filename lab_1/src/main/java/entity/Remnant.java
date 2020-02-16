package entity;

public class Remnant {
    private int productId;
    private int warehouseId;
    private int amount;

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
    public String toString() {
        return "Remnant{" +
                "productId=" + productId +
                ", warehouseId=" + warehouseId +
                ", amount=" + amount +
                '}';
    }
}
