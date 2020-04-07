package repository;

import entity.Product;
import entity.Remnant;
import entity.RemnantContainer;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

public class RemnantRepositoryXML extends XMLDatabase<RemnantContainer> implements RemnantRepository {

    public RemnantRepositoryXML(String tableName) throws RepositoryException {
        super(tableName, new RemnantContainer());
    }

    @Override
    public int getProductAmount(Product product) throws RepositoryException {
        return getAllByProduct(product).stream().mapToInt(Remnant::getAmount).sum();
    }

    private Remnant get(Remnant remnant) throws RepositoryException {
        System.out.println("get remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        List<Remnant> found = entity.getRemnants()
                .stream()
                .filter(elem -> elem.getProductId() == remnant.getProductId() && elem.getWarehouseId() == remnant.getWarehouseId())
                .collect(Collectors.toList());
        return found.size() != 0 ? found.get(0) : null;
    }

    @Override
    public List<Remnant> getAllByProduct(Product product) throws RepositoryException {
        System.out.println("get all remnants with product id = " + product.getId());
        return entity.getRemnants().stream().filter(elem -> elem.getProductId() == product.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException {
        System.out.println("get all remnants with warehouse id = " + warehouse.getId());
        return entity.getRemnants().stream().filter(elem -> elem.getWarehouseId() == warehouse.getId()).collect(Collectors.toList());
    }

    @Override
    public Remnant insert(Remnant remnant) throws RepositoryException {
        System.out.println("insert remnant  " + remnant);
        Remnant foundDB = get(remnant);
        if (foundDB == null) {
            entity.getRemnants().add(remnant);
            saveToDataBase();
        } else {
            throw new RepositoryException("Such Remnant already exist");
        }
        return remnant;
    }

    @Override
    public Remnant update(Remnant remnant) throws RepositoryException {
        System.out.println("update remnant " + remnant);
        Remnant fromDB = get(remnant);
        if (fromDB != null) {
            delete(fromDB);
            entity.getRemnants().add(remnant);
            saveToDataBase();
        } else {
            throw new RepositoryException("No remnant found with such id's");
        }
        return remnant;
    }

    @Override
    public void delete(Remnant remnant) throws RepositoryException {
        System.out.println("delete remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        Remnant fromDB = get(remnant);
        if (fromDB != null) {
            entity.getRemnants().remove(fromDB);
            saveToDataBase();
        }
    }
}
