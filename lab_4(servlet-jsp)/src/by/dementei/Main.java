package by.dementei;

import by.dementei.entity.Product;
import by.dementei.entity.Remnant;
import by.dementei.entity.Warehouse;
import by.dementei.service.ProductService;
import by.dementei.service.RemnantService;
import by.dementei.service.WarehouseService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


public class Main extends HttpServlet {

    private ProductService productService;
    private WarehouseService warehouseService;
    private RemnantService remnantService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService = ProductService.getProductService();
        warehouseService = WarehouseService.getWarehouseService();
        remnantService = RemnantService.getRemnantService();
        try {
            System.out.println(warehouseService.getALLWarehouses());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("action", action);

        try {
            switch (action) {
                case "addWarehouse":
                    Warehouse warehouse = new Warehouse(0, request.getParameter("warehouseTitle"), request.getParameter("warehouseLocation"));
                    warehouseService.insertWarehouse(warehouse);
                    request.setAttribute("action", "");
                    break;
                case "addProduct":
                    Product product = new Product(0, request.getParameter("productName"), request.getParameter("productDescription"));
                    productService.insertProduct(product);
                    request.setAttribute("action", "");
                    break;
                case "addRemnant":
                    Remnant remnant = new Remnant(Integer.parseInt(request.getParameter("productId")), Integer.parseInt(request.getParameter("warehouseId")),
                            Integer.parseInt(request.getParameter("remnantAmount")));
                    remnantService.insert(remnant);
                    request.setAttribute("action", "");
                    break;
                case "openWarehouse":
                    warehouse = new Warehouse(Integer.parseInt(request.getParameter("id")), request.getParameter("warehouseTitle"), request.getParameter("warehouseLocation"));
                    warehouseService.updateWarehouse(warehouse);
                    request.setAttribute("action", "");
                    break;
                case "openProduct":
                    product = new Product(Integer.parseInt(request.getParameter("id")), request.getParameter("productName"), request.getParameter("productDescription"));
                    productService.updateProduct(product);
                    request.setAttribute("action", "");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {


            if (action == null || "".equals(request.getAttribute("action"))) {
                request.setAttribute("action", null);
                request.setAttribute("warehouses", warehouseService.getALLWarehouses());
                request.setAttribute("products", productService.getALLProducts());
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }
            request.setAttribute("action", action);
            switch (action) {
                case "deleteProduct":
                    productService.deleteProduct(new Product(Integer.parseInt(request.getParameter("id")), null, null));
                    request.setAttribute("warehouses", warehouseService.getALLWarehouses());
                    request.setAttribute("products", productService.getALLProducts());
                    break;

                case "deleteWarehouse":
                    warehouseService.deleteWarehouse(new Warehouse(Integer.parseInt(request.getParameter("id")), null, null));
                    request.setAttribute("warehouses", warehouseService.getALLWarehouses());
                    request.setAttribute("products", productService.getALLProducts());
                    break;

                case "openWarehouse":
                    Warehouse warehouse = warehouseService.getWarehouseById(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("title", warehouse.getTitle());
                    request.setAttribute("location", warehouse.getLocation());
                    break;
                case "openProduct":
                    Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("description", product.getDescription());
                    request.setAttribute("name", product.getName());
                    break;
                case "addRemnant":
                    warehouse = warehouseService.getWarehouseById(Integer.parseInt(request.getParameter("warehouseId")));
                    product = productService.getProductById(Integer.parseInt(request.getParameter("productId")));

                    request.setAttribute("title", warehouse.getTitle());
                    request.setAttribute("location", warehouse.getLocation());
                    request.setAttribute("description", product.getDescription());
                    request.setAttribute("name", product.getName());
                    break;
                default:
                    request.setAttribute("status", "404");
                    break;
            }

            request.getRequestDispatcher("/").forward(request, response);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
