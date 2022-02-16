package factorySession;

import dao.UserDAO;
import dao.UserDaoImpl;

public class PostgresDaoFactory extends DaoMyFactorySession {
    private static UserDAO instance = null; //new UserDaoImpl();

    @Override
    public UserDAO getUserDao() {
        if (instance == null)
            instance = new UserDaoImpl();
        return instance;
    }
}
