import exception.RepositoryException;
import repository.*;
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

            RemnantRepository remnantRepositoryJSON = new RemnantRepositoryJSON("remnants");
            RemoteService jsonService = new RemoteServiceImplementation(new ProductRepositoryJSON("products", remnantRepositoryJSON),
                    new WarehouseRepositoryJSON("warehouses", remnantRepositoryJSON), remnantRepositoryJSON);
            context.rebind("rmi://localhost:8080/json-server", jsonService);

            RemnantRepository remnantRepositoryXML = new RemnantRepositoryXML("remnants");
            RemoteService xmlService = new RemoteServiceImplementation(new ProductRepositoryXML("products", remnantRepositoryXML),
                    new WarehouseRepositoryXML("warehouses", remnantRepositoryXML), remnantRepositoryXML);
            context.rebind("rmi://localhost:8080/xml-server", xmlService);
        } catch (RepositoryException | ClassNotFoundException e) {
            throw new RemoteException(e.getMessage(), e.getCause());
        }
    }
}
