package andres.learning.marketplace.app.service;

import andres.learning.marketplace.app.database.AppOrderDatabase;
import andres.learning.marketplace.products.database.ProductDatabase;
import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.products.service.ProductService;

import javax.sql.DataSource;

public class AppOrderService {

    AppOrderDatabase database;
    ProductService productService;
    public AppOrderService(DataSource connectionPool){
        database = new AppOrderDatabase(connectionPool);
        productService = new ProductService(connectionPool);
    }


    public Product createOrder(int clientId, int productId) {
        Product orderedProduct = null;
        if(database.createOrder(clientId, productId)){
            orderedProduct = productService.getById(productId);
        }
        return orderedProduct;
    }
}
