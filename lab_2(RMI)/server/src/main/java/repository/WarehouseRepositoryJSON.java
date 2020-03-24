package repository;

import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarehouseRepositoryJSON extends JSONDatabase implements WarehouseRepository {
    private final String ID = "id";
    private final String TITLE = "title";
    private final String LOCATION = "location";
    private final RemnantRepository remnantRepository;

    public WarehouseRepositoryJSON(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName);
        this.remnantRepository = remnantRepository;
    }

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

    @Override
    public List<Warehouse> getALL() throws RepositoryException {
        System.out.println("get all warehouses");
        try {
            return Arrays.asList(document.read("$.warehouse.*", Warehouse[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

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

