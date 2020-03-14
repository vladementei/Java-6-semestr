import exception.RepositoryException;
import repository.ProductRepositorySQL;
import repository.RemnantRepositorySQL;
import repository.WarehouseRepositorySQL;
import service.RemoteService;
import service.RemoteServiceImplementation;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException, NamingException {
        try {
            Context context = new InitialContext();
            RemoteService sqlService = new RemoteServiceImplementation(new ProductRepositorySQL("products"),
                    new WarehouseRepositorySQL("warehouses"), new RemnantRepositorySQL("product_warehouse"));
            context.rebind("rmi://localhost:8080/sql-server", sqlService);
        } catch (RepositoryException | ClassNotFoundException e) {
            throw new RemoteException(e.getMessage(), e.getCause());
        }
    }
}
