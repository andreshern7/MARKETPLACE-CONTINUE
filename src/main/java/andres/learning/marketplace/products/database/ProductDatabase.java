package andres.learning.marketplace.products.database;

import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.utilities.Connection;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDatabase {

    private java.sql.Connection connection;

    public ProductDatabase(DataSource connectionPool) {
        connection = Connection.getConnection(connectionPool);
    }

    public Product getById(int id) throws SQLException {

        Product resultProduct;
        String query = "SELECT * FROM products WHERE productId=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        result.beforeFirst();
        if (!result.next()) {
            throw new SQLException("Product with id: " + id + " not exist");
        }else{
            resultProduct = createProductObject(result);
        }
        preparedStatement.close();
        return resultProduct;
    }

    public int lastProductId() {
        String query = "SELECT * FROM products;";
        int lastId=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                lastId = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public Product insertProduct(String name, String fileNamePath, String description,
                                int price) {

        String query = "INSERT INTO products VALUES(?, ?, ?, ?, ?);";
        Product result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fileNamePath);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, price);
            preparedStatement.execute();
            result = getById(lastProductId());
            System.out.println("INSERT PRODUCT DATABASE: PRODUCT INSERTED SUCCESSFULLY");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Product deleteProduct(int id){
        Product erasedProduct = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE productId=?");
            preparedStatement.setInt(1, id);
            erasedProduct = getById(id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return erasedProduct;
    }


    public Product editProduct(int id, String name, String productImageFile, String description, int price){
        Product modifiedProduct = null;
        try {
            String query = "UPDATE products SET name=?, photoFileName=?, description=?, price=? WHERE (productId = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, productImageFile);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5, id);
            System.out.println("PRODUCT DATABASE EDITPRODUCT: "+preparedStatement);
            System.out.println("PRODUCT DATABASE EDITPRODUCT: "+getById(id));
            preparedStatement.executeUpdate();
            modifiedProduct = getById(id);
            System.out.println("PRODUCT DATABASE EDITPRODUCT: "+modifiedProduct);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modifiedProduct;
    }

    private Product createProductObject(ResultSet resultSet) {
        Product foundProduct = null;
        try {
            resultSet.beforeFirst();
            if (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String photoFileName = resultSet.getString(3);
                String description = resultSet.getString(4);
                int price = resultSet.getInt(5);
                foundProduct = new Product(productId, name, photoFileName, description, price);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
    }

}
