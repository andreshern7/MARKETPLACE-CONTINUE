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

    public Product insertProduct(String name, String lastName, String address,
                                String email, String username, String password) {

        String query = "INSERT INTO products VALUES(?, ?, ?, ?, ?);";
        Product result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.execute();
            result = getById(lastProductId());
            System.out.println("INSERT PRODUCT DATABASE: PRODUCT INSERTED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
                /*
                    private int id;
                    private String name;
                    private String photoFileName;
                    private String description;
                    private int price;
                 */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProduct;
    }
}
