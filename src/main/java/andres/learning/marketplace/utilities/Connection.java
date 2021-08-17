package andres.learning.marketplace.utilities;

import javax.sql.DataSource;
import java.sql.*;

public class Connection {

    static java.sql.Connection connection = null;

    public static java.sql.Connection getConnection(DataSource connectionPool) {
        if(connection==null) {
            try {
                connection = connectionPool.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
