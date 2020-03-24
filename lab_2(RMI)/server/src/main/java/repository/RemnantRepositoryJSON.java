package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.Arrays;
import java.util.List;

public class RemnantRepositoryJSON extends JSONDatabase implements RemnantRepository {
    private final String PRODUCT_ID = "productId";
    private final String WAREHOUSE_ID = "warehouseId";
    private final String AMOUNT = "amount";

    public RemnantRepositoryJSON(String tableName) throws RepositoryException {
        super(tableName);
    }

    @Override
    public int getProductAmount(Product product) throws RepositoryException {
        return getAllByProduct(product).stream().mapToInt(Remnant::getAmount).sum();
    }

    private Remnant get(Remnant remnant) throws RepositoryException {
        System.out.println("get remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        try {
            Remnant[] found = document.read("$.remnant.[?(@." + PRODUCT_ID + " == " + remnant.getProductId() +
                    " && @." + WAREHOUSE_ID + " == " + remnant.getWarehouseId() + ")]", Remnant[].class);
            if (found.length == 0) {
                return null;
            } else {
                return found[0];
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }

    }

    @Override
    public List<Remnant> getAllByProduct(Product product) throws RepositoryException {
        System.out.println("get all remnants with product id = " + product.getId());
        try {
            return Arrays.asList(document.read("$.remnant.[?(@." + PRODUCT_ID + " == " + product.getId() + ")]", Remnant[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException {
        System.out.println("get all remnants with warehouse id = " + warehouse.getId());
        try {
            return Arrays.asList(document.read("$.remnant.[?(@." + WAREHOUSE_ID + " == " + warehouse.getId() + ")]", Remnant[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    @Override
    public Remnant insert(Remnant remnant) throws RepositoryException {
        System.out.println("insert remnant  " + remnant);
        try {
            Remnant foundDB = get(remnant);
            if (foundDB == null) {
                document.add(".remnant", remnant);
                saveToDataBase();
            } else {
                throw new RepositoryException("Such Remnant already exist");
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return remnant;
    }

    @Override
    public Remnant update(Remnant remnant) throws RepositoryException {
        System.out.println("update remnant " + remnant);
        try {
            Remnant fromDB = get(remnant);
            if (fromDB != null) {
                delete(fromDB);
                document.add(".remnant", remnant);
                saveToDataBase();
            } else {
                throw new RepositoryException("No remnant found with such id's");
            }
            return remnant;
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    @Override
    public void delete(Remnant remnant) throws RepositoryException {
        System.out.println("delete remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        try {
            document.delete("$.remnant.[?(@." + PRODUCT_ID + " == " + remnant.getProductId() +
                    " && @." + WAREHOUSE_ID + " == " + remnant.getWarehouseId() + ")]");
            saveToDataBase();
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}


