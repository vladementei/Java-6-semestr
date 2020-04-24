package repository;

import entity.Product;
import entity.Remnant;
import entity.RemnantContainer;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Implementation of {@link XMLDatabase} for work with {@link Remnant remnants}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantRepositoryXML extends XMLDatabase<RemnantContainer> implements RemnantRepository {

    /**
     * constructor to create repository for specified table
     * @param tableName name of XML database
     * @throws RepositoryException if connection to database failed
     */
    public RemnantRepositoryXML(String tableName) throws RepositoryException {
        super(tableName, new RemnantContainer());
    }

    /**
     * get sum amount of specified {@link Product product} on all warehouses
     * @param product {@link Product product} to search
     * @return sum amount
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public int getProductAmount(Product product) throws RepositoryException {
        return getAllByProduct(product).stream().mapToInt(Remnant::getAmount).sum();
    }

    /**
     * get specified {@link Remnant remnant} from XML database
     * @param remnant instance to search
     * @return result {@link Remnant remnant} from database
     * @throws RepositoryException if parser throws any error
     */
    private Remnant get(Remnant remnant) throws RepositoryException {
        System.out.println("get remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        List<Remnant> found = entity.getRemnants()
                .stream()
                .filter(elem -> elem.getProductId() == remnant.getProductId() && elem.getWarehouseId() == remnant.getWarehouseId())
                .collect(Collectors.toList());
        return found.size() != 0 ? found.get(0) : null;
    }

    /**
     * get all {@link Remnant remnants} from XML database that has reference to specified {@link Product product}
     * @param product {@link Product product} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Remnant> getAllByProduct(Product product) throws RepositoryException {
        System.out.println("get all remnants with product id = " + product.getId());
        return entity.getRemnants().stream().filter(elem -> elem.getProductId() == product.getId()).collect(Collectors.toList());
    }

    /**
     * get all {@link Remnant remnants} from XML database that has reference to specified {@link Warehouse warehouse}
     * @param warehouse {@link Warehouse warehouse} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException {
        System.out.println("get all remnants with warehouse id = " + warehouse.getId());
        return entity.getRemnants().stream().filter(elem -> elem.getWarehouseId() == warehouse.getId()).collect(Collectors.toList());
    }

    /**
     * add new {@link Remnant remnant} to XML database
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} from XML database
     * @throws RepositoryException if parser throws any error
     */
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

    /**
     * update {@link Remnant remnant} in XML database
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} from XML database
     * @throws RepositoryException if parser throws any error
     */
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

    /**
     * delete {@link Remnant remnant} from XML database
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RepositoryException if  parser throws any error
     */
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
