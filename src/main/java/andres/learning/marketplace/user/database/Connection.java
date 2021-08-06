package andres.learning.marketplace.user.database;

import javax.sql.DataSource;
import java.sql.*;

public class Connection {

    private java.sql.Connection connection;

    public Connection(DataSource connectionPool) {
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getById(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE clientId=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        result.beforeFirst();
        if (!result.next()) {
            throw new SQLException("Client with id: " + id + " not exist");
        }
        return result;
    }

    private int lastClientId() {
        String query = "SELECT * FROM clients;";
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


    public ResultSet createUser(String name, String lastName, String address,
                                String email, String username, String password) {

        String query = "INSERT INTO clients VALUES(?, ?, ?, ?, ?, ?, ?);";
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, password);
            preparedStatement.execute();
            result = getById(lastClientId());
            System.out.println("createUser: CLIENT INSERTED SUCCESSFULLY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet userLogin(String username, String password){
        String query = "SELECT * FROM clients WHERE (username=? and password=?);";
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}