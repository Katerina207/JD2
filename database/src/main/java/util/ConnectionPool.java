package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionPool {

    private static DataSource DATA_SOURCE;

    static {
        initDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(PropertyManager.get("db.url"));
        poolProperties.setUsername(PropertyManager.get("db.user"));
        poolProperties.setPassword(PropertyManager.get("db.password"));
        DATA_SOURCE = new DataSource(poolProperties);
    }

    private static void initDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}