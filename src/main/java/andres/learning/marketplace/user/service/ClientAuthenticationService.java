package andres.learning.marketplace.user.service;

import andres.learning.marketplace.user.database.ClientAuthenticationDatabase;
import andres.learning.marketplace.user.model.ResponseUser;
import andres.learning.marketplace.user.model.Client;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAuthenticationService {

    ClientAuthenticationDatabase database;

    public ClientAuthenticationService(DataSource connectionPool) {

        database = new ClientAuthenticationDatabase(connectionPool);
    }

    public ResponseUser createClient(String name, String lastName, String address, String email,
                                     String username, String password) {
        Client client = new Client(name, lastName, address, email, username,password);
        ResponseUser foundClientResponse = null;
        if(client.validClient()) {
            ResultSet newClient = database.createUser(name, lastName, address, email, username, password);
            try {
                newClient.beforeFirst();
                foundClientResponse = responseUserObject(newClient);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return foundClientResponse;
    }

    public ResponseUser userLogin(String username, String password) throws SQLException {
        ResultSet userDbData = database.userLogin(username, password);
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
                Client foundClientById = new Client(userId, name, lastName, userName, password, country, technology);
                foundUserResponse = new ResponseUser(foundClientById);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUserResponse;
    }
}
