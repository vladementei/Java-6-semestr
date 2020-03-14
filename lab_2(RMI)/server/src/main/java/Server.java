import service.ProductService;
import service.WarehouseService;

import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(ProductService.getProductService().getALLProducts());
        System.out.println(WarehouseService.getWarehouseService().getALLWarehouses());
    }
}
