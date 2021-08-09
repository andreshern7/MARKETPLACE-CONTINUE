package andres.learning.marketplace.products.service;

import andres.learning.marketplace.products.database.ProductDatabase;
import andres.learning.marketplace.products.model.Product;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductService {


    private ProductDatabase database;

    public ProductService(DataSource connectionPool){
        database = new ProductDatabase(connectionPool);
    }

    public ArrayList<Product> allProducts(){
        int amountProducts = database.lastProductId();
        System.out.println(amountProducts);
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 1; i <= amountProducts; i++) {
            try {
                productList.add(database.getById(i));
                System.out.println("ALL PRODUCTS SERVICE: "+productList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productList;
    }

    public Product getById(int id){
        Product product = null;
        try {
             product = database.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


}
