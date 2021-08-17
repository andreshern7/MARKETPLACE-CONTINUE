package andres.learning.marketplace.admin.service;

import andres.learning.marketplace.user.database.ClientAuthenticationDatabase;
import andres.learning.marketplace.user.model.Client;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAuthenticationService {

    ClientAuthenticationDatabase database;

    public AdminAuthenticationService(DataSource connectionPool) {

        database = new ClientAuthenticationDatabase(connectionPool);
    }



    public Client adminLogin(String username, String password) throws SQLException {
        ResultSet userDbData = database.userLogin(username, password);
        Client foundClientResponse = null;
        if (userDbData.next()) {
            String adminUsername = userDbData.getString("username");
            String adminPassword = userDbData.getString("password");
            System.out.println("ADMINLOGIN SERVICE USERNAME: "+adminUsername);
            System.out.println("ADMINLOGIN SERVICE PASSWORD: "+adminPassword);
            if(adminUsername.equals("jorgyp") && adminPassword.equals("admin")){
                foundClientResponse = responseUserObject(userDbData);
                System.out.println("ADMINLOGIN SERVICE: "+foundClientResponse);
            } else {
                throw new SQLException("Invalid User Data");
            }
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
