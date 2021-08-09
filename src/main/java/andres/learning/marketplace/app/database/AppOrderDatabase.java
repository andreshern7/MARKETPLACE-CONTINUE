package andres.learning.marketplace.app.database;

import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.utilities.Connection;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppOrderDatabase {

    java.sql.Connection connection;
    public AppOrderDatabase(DataSource connectionPool){
        connection = Connection.getConnection(connectionPool);
    }

    public boolean createOrder(int clientId, int productId){

        String query = "INSERT INTO orders values(?, ?, ?)";
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setInt(3, productId);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
