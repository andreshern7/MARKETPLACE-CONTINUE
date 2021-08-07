package andres.learning.marketplace.utilities;

import javax.sql.DataSource;
import java.sql.*;

public class Connection {

    public static java.sql.Connection getConnection(DataSource connectionPool) {
        java.sql.Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
