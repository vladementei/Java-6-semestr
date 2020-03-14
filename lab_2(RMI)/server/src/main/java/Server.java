import exception.RepositoryException;
import repository.ProductRepositorySQL;
import repository.RemnantRepositorySQL;
import repository.WarehouseRepositorySQL;
import service.RemoteService;
import service.RemoteServiceImplementation;

import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException {
        try {

            RemoteService remoteService = new RemoteServiceImplementation(new ProductRepositorySQL("products"),
                    new WarehouseRepositorySQL("warehouses"), new RemnantRepositorySQL("product_warehouse"));
            System.out.println(remoteService.getALLProducts());
            System.out.println(remoteService.getALLWarehouses());
        } catch (RepositoryException | ClassNotFoundException e) {
            throw new RemoteException(e.getMessage(), e.getCause());
        }
    }
}
