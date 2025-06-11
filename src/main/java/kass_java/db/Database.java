package kass_java.db;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // private static final String CONNECTION_STRING =
    // "jdbc:sqlserver://localhost:1433;databaseName=kass;" +
    // "integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    // private static Connection con;

    public static Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException("DB холболт идэвхгүй");
    }
}
