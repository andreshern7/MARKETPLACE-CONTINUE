package andres.learning.marketplace.user.service;

import andres.learning.marketplace.user.database.ClientAuthenticationDatabase;

import andres.learning.marketplace.user.model.Client;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAuthenticationService {

    ClientAuthenticationDatabase database;

    public ClientAuthenticationService(DataSource connectionPool) {

        database = new ClientAuthenticationDatabase(connectionPool);
    }

    public Client createClient(String name, String lastName, String address, String email,
                               String username, String password) {
        Client client = new Client(name, lastName, address, email, username,password);
        if(client.validClient()) {
            ResultSet newClient = database.createUser(name, lastName, address, email, username, password);
            try {
                newClient.beforeFirst();
                client = responseUserObject(newClient);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public Client userLogin(String username, String password) throws SQLException {
        ResultSet userDbData = database.userLogin(username, password);
        Client foundClientResponse = null;
        if (userDbData.next()) {
            System.out.println("USERLOGIN CLIENT SERVICE: "+userDbData.getString("userName"));
            System.out.println("USERLOGIN CLIENT SERVICE: "+userDbData.getString("password"));
            foundClientResponse = responseUserObject(userDbData);
            System.out.println(foundClientResponse);
        } else {
            throw new SQLException("Invalid User Data");
        }
        return foundClientResponse;
    }

    /**
     * Create a Client by the content of the resultset
     * @param resultSet
     * @return Client
     */

    private Client responseUserObject(ResultSet resultSet) {
        Client foundClientResponse = null;
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
                foundClientResponse = new Client(userId, name, lastName, userName, password, country, technology);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundClientResponse;
    }
}
