package repository;

import entity.Remnant;
import entity.Warehouse;
import entity.WarehouseContainer;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseRepositoryXML extends XMLDatabase<WarehouseContainer> implements WarehouseRepository {
    private final RemnantRepository remnantRepository;


    public WarehouseRepositoryXML(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName, new WarehouseContainer());
        this.remnantRepository = remnantRepository;
    }

    @Override
    public Warehouse get(int id) throws RepositoryException {
        System.out.println("get warehouse with id = " + id);
        List<Warehouse> warehouses = entity.getWarehouses().stream().filter(warehouse -> warehouse.getId() == id).collect(Collectors.toList());
        return warehouses.size() != 0 ? warehouses.get(0) : null;
    }

    @Override
    public List<Warehouse> getALL() throws RepositoryException {
        System.out.println("get all warehouses");
        return entity.getWarehouses();
    }

    @Override
    public Warehouse insert(Warehouse warehouse) throws RepositoryException {
        System.out.println("insert warehouse  " + warehouse);
        int maxId = entity.getWarehouses().stream().map(Warehouse::getId).max(Integer::compareTo).orElse(0);
        warehouse.setId(maxId + 1);
        entity.getWarehouses().add(warehouse);
        saveToDataBase();
        return warehouse;
    }

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
