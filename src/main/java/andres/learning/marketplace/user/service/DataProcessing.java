package andres.learning.marketplace.user.service;

import andres.learning.marketplace.user.database.Connection;
import andres.learning.marketplace.user.model.ResponseUser;
import andres.learning.marketplace.user.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataProcessing {

    Connection connection;

    public DataProcessing(DataSource connectionPool) {
        connection = new Connection(connectionPool);
    }

    public ResponseUser createClient(String name, String lastName, String address, String email,
                                     String username, String password) {
        //User newUser = new User(name, lastName, userName, password, country, technology);
        ResultSet newUser = connection.createUser(name, lastName, address, email, username, password);
        ResponseUser foundUserResponse = null;
        try {
            newUser.beforeFirst();
            foundUserResponse = responseUserObject(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUserResponse;
    }

    public ResponseUser userLogin(String username, String password) throws SQLException {
        ResultSet userDbData = connection.userLogin(username, password);
        ResponseUser foundUserResponse = null;
        if (userDbData.next()) {
            System.out.println(userDbData.getString("userName"));
            System.out.println(userDbData.getString("password"));
            foundUserResponse = responseUserObject(userDbData);
            System.out.println(foundUserResponse);
        } else {
            throw new SQLException("Invalid User Data");
        }
        return foundUserResponse;
    }

    private ResponseUser responseUserObject(ResultSet resultSet) {
        ResponseUser foundUserResponse = null;
        try {
            resultSet.beforeFirst();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String password = resultSet.getString(5);
                String country = resultSet.getString(6);
                String technology = resultSet.getString(7);
                User foundUserById = new User(userId, name, lastName, userName, password, country, technology);
                foundUserResponse = new ResponseUser(foundUserById);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUserResponse;
    }
}
