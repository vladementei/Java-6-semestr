package repository;

import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <b>Implementation of {@link JSONDatabase} for work with {@link Warehouse warehouses}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class WarehouseRepositoryJSON extends JSONDatabase implements WarehouseRepository {
    /**
     * attribute "id" name in JSON database
     */
    private final String ID = "id";

    /**
     * attribute "title" name in JSON databse
     */
    private final String TITLE = "title";

    /**
     * attribute "location" name in JSON database
     */
    private final String LOCATION = "location";

    /**
     * instance of {@link RemnantRepository remnant repository} to implement cascade delete
     */
    private final RemnantRepository remnantRepository;

    /**
     * constructor to create repository for specified table
     * @param tableName name of JSON database
     * @param remnantRepository instance of {@link RemnantRepository remnant repository}
     * @throws RepositoryException if connection to database failed
     */
    public WarehouseRepositoryJSON(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName);
        this.remnantRepository = remnantRepository;
    }


    /**
     * get {@link Warehouse warehouse} from JSON database by id
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param from JSON database
     * @throws RepositoryException if parser throws any error happened
     */
    @Override
    public Warehouse get(int id) throws RepositoryException {
        System.out.println("get warehouse with id = " + id);
        try {
            Warehouse[] warehouses = document.read("$.warehouse.[?(@." + ID + " == " + id + ")]", Warehouse[].class);
            if (warehouses.length != 0) {
                return warehouses[0];
            } else {
                return null;
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get all {@link Warehouse warehouses} from JSON database
     * @return list of all {@link Warehouse warehouses}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Warehouse> getALL() throws RepositoryException {
        System.out.println("get all warehouses");
        try {
            return Arrays.asList(document.read("$.warehouse.*", Warehouse[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * add new {@link Warehouse warehouse} to JSON database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Warehouse insert(Warehouse warehouse) throws RepositoryException {
        System.out.println("insert warehouse  " + warehouse);
        try {
            warehouse.setId(Collections.max(Arrays.asList(document.read("$..id", Integer[].class))) + 1);
        } catch (Throwable e) {
            warehouse.setId(1);
        }
        try {
            document.add(".warehouse", warehouse);
            saveToDataBase();
            return warehouse;
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * update {@link Warehouse warehouse} in JSON database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Warehouse update(Warehouse warehouse) throws RepositoryException {
        System.out.println("update warehouse " + warehouse);
        Warehouse fromDB = get(warehouse.getId());
        if (fromDB != null) {
            document.delete("$.warehouse.[?(@." + ID + " == " + warehouse.getId() + ")]");
            //delete(fromDB.getId());
            try {
                document.add(".warehouse", warehouse);
            } catch (Throwable e) {
                throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
            }
            saveToDataBase();
        } else {
            throw new RepositoryException("No warehouse with id == " + warehouse.getId());
        }
        return warehouse;
    }

    /**
     * delete {@link Warehouse warehouse} from JSON database
     * @param id id of {@link Warehouse warehouse} wishing to delete
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public void delete(int id) throws RepositoryException {
        System.out.println("delete warehouse with id = " + id);
        try {
            document.delete("$.warehouse.[?(@." + ID + " == " + id + ")]");
            saveToDataBase();
            for (Remnant remnant : remnantRepository.getAllByWarehouse(new Warehouse(id, "", ""))) {
                remnantRepository.delete(remnant);
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}

