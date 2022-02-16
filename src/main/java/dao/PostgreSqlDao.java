package dao;

import java.sql.Connection;
import java.sql.DriverManager;

@Deprecated
public class PostgreSqlDao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL    = "jdbc:postgresql://localhost:5432/demo";
    private static final String LOGIN  = "postgres";
    private static final String PASS   = "postgres";

    protected Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error load driver;");
            e.printStackTrace();
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, LOGIN, PASS);
        } catch (Exception e) {
            System.out.println("Error get connection;");
            e.printStackTrace();
        }
        return c;
    }
}
