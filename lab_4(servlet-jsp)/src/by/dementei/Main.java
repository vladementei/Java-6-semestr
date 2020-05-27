package by.dementei;

import by.dementei.entity.Product;
import by.dementei.entity.Remnant;
import by.dementei.entity.Warehouse;
import by.dementei.service.ProductService;
import by.dementei.service.RemnantService;
import by.dementei.service.WarehouseService;
import javafx.util.Pair;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

                case "deleteRemnant":
                    remnantService.delete(new Remnant(Integer.parseInt(request.getParameter("productId")), Integer.parseInt(request.getParameter("id")), 0));
                    request.setAttribute("action", "openWarehouse");
                case "openWarehouse":
                    Warehouse warehouse = warehouseService.getWarehouseById(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("title", warehouse.getTitle());
                    request.setAttribute("location", warehouse.getLocation());
                    request.setAttribute("warehouseId", warehouse.getId());
                    List<Pair<Product, Integer>> remnants = remnantService.getAllByWarehouse(warehouse).stream()
                            .map(remnant -> {
                                try {
                                    return new Pair<>(productService.getProductById(remnant.getProductId()), remnant.getAmount());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            })
                            .filter(Objects::nonNull).collect(Collectors.toList());
                    request.setAttribute("remnants", remnants);
                    break;
                case "openProduct":
                    Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("description", product.getDescription());
                    request.setAttribute("name", product.getName());
                    request.setAttribute("totalAmount", remnantService.getProductAmount(product));
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
                    break;
            }
            request.getRequestDispatcher("/").forward(request, response);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("action", action);

        try {
            switch (action) {
                case "addWarehouse":
                    warehouseService.insertWarehouse(new Warehouse(0, request.getParameter("warehouseTitle"), request.getParameter("warehouseLocation")));
                    break;
                case "addProduct":
                    productService.insertProduct(new Product(0, request.getParameter("productName"), request.getParameter("productDescription")));
                    break;
                case "addRemnant":
                    Remnant remnant = new Remnant(Integer.parseInt(request.getParameter("productId")), Integer.parseInt(request.getParameter("warehouseId")),
                            Integer.parseInt(request.getParameter("remnantAmount")));
                    remnantService.insert(remnant);
                    break;
                case "openWarehouse":
                    warehouseService.updateWarehouse(new Warehouse(Integer.parseInt(request.getParameter("id")), request.getParameter("warehouseTitle"),
                            request.getParameter("warehouseLocation")));
                    break;
                case "openProduct":
                    productService.updateProduct(new Product(Integer.parseInt(request.getParameter("id")), request.getParameter("productName"),
                            request.getParameter("productDescription")));
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("action", "");
        doGet(request, response);
    }
}
