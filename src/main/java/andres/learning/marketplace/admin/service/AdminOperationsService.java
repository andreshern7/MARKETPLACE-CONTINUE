package andres.learning.marketplace.admin.service;

import andres.learning.marketplace.products.database.ProductDatabase;
import andres.learning.marketplace.products.model.Product;

import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminOperationsService {


    private ProductDatabase database;

    public AdminOperationsService(DataSource connectionPool){
        database = new ProductDatabase(connectionPool);
    }


    public Product createProduct(String name, Part productImageFile, String description, int price) {

        String productImageFileName = extractFileName(productImageFile);
        savePictureFile(productImageFile);
        Product result = database.insertProduct(name, productImageFileName, description, price);
        return result;
    }

    public Product deleteProduct(int id){
        return (database.deleteProduct(id));
    }

    public Product editProduct(int id, String name, Part productImageFile, String description, int price){

        String productImageFileName = extractFileName(productImageFile);
        savePictureFile(productImageFile);
        return (database.editProduct(id, name, productImageFileName, description, price));
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


    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void savePictureFile(Part PictureFile){
        String local =  "C:/Users/Andres/Documents/Andres/MyLearning/Programming/Languages/Java/JavaWeb/1.practice/" +
                "marketplace/src/main/webapp/pictures/";

        String server =  "C:/Users/Andres/Documents/Andres/MyLearning/Programming/Languages/Java/JavaWeb/1.practice/" +
                "marketplace/target/marketplace-1.0-SNAPSHOT/pictures/";
        String productImageFileName = extractFileName(PictureFile);
        try {
            PictureFile.write(server + productImageFileName);
            PictureFile.write(local + productImageFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
