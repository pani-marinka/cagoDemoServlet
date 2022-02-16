package factorySession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class.getName());


    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        LOG.info("Excepted get connection");
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/demo");
            c = ds.getConnection();
        } catch (NamingException e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            LOG.info(e.getMessage());
            e.printStackTrace();
        }
        LOG.info("Connection success");
        return c;
    }
}