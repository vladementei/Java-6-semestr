package repository;

import entity.Remnant;
import entity.Warehouse;
import entity.WarehouseContainer;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Implementation of {@link XMLDatabase} for work with {@link Warehouse warehouses}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class WarehouseRepositoryXML extends XMLDatabase<WarehouseContainer> implements WarehouseRepository {
    /**
     * instance of {@link RemnantRepository remnant repository} to implement cascade delete
     */
    private final RemnantRepository remnantRepository;

    /**
     * constructor to create repository for specified table
     * @param tableName name of XML database
     * @param remnantRepository instance of {@link RemnantRepository remnant repository}
     * @throws RepositoryException if connection to database failed
     */
    public WarehouseRepositoryXML(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName, new WarehouseContainer());
        this.remnantRepository = remnantRepository;
    }

    /**
     * get {@link Warehouse warehouse} from XML database by id
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param from XML database
     * @throws RepositoryException if parser throws any error happened
     */
    @Override
    public Warehouse get(int id) throws RepositoryException {
        System.out.println("get warehouse with id = " + id);
        List<Warehouse> warehouses = entity.getWarehouses().stream().filter(warehouse -> warehouse.getId() == id).collect(Collectors.toList());
        return warehouses.size() != 0 ? warehouses.get(0) : null;
    }

    /**
     * get all {@link Warehouse warehouses} from XML database
     * @return list of all {@link Warehouse warehouses}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Warehouse> getALL() throws RepositoryException {
        System.out.println("get all warehouses");
        return entity.getWarehouses();
    }

    /**
     * add new {@link Warehouse warehouse} to XML database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} from XML database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Warehouse insert(Warehouse warehouse) throws RepositoryException {
        System.out.println("insert warehouse  " + warehouse);
        int maxId = entity.getWarehouses().stream().map(Warehouse::getId).max(Integer::compareTo).orElse(0);
        warehouse.setId(maxId + 1);
        entity.getWarehouses().add(warehouse);
        saveToDataBase();
        return warehouse;
    }

    /**
     * update {@link Warehouse warehouse} in XML database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} from XML database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Warehouse update(Warehouse warehouse) throws RepositoryException {
        System.out.println("update warehouse " + warehouse);
        Warehouse fromDB = get(warehouse.getId());
        if (fromDB != null) {
            entity.getWarehouses().remove(fromDB);
            entity.getWarehouses().add(warehouse);
            saveToDataBase();
        } else {
            throw new RepositoryException("No warehouse with id == " + warehouse.getId());
        }
        return warehouse;
    }

    /**
     * delete {@link Warehouse warehouse} from XML database
     * @param id id of {@link Warehouse warehouse} wishing to delete
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public void delete(int id) throws RepositoryException {
        System.out.println("delete warehouse with id = " + id);
        Warehouse fromDB = get(id);
        if (fromDB != null){
            entity.getWarehouses().remove(fromDB);
            saveToDataBase();
        }
        for (Remnant remnant : remnantRepository.getAllByWarehouse(new Warehouse(id, "", ""))) {
            remnantRepository.delete(remnant);
        }
    }
}
