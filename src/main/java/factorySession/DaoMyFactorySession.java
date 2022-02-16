package factorySession;


import dao.UserDAO;

public abstract  class DaoMyFactorySession {
    private static DaoMyFactorySession instance = null; //new postgresDaoFactory();

    public static DaoMyFactorySession getInstance() {
        if (instance==null)
            instance = new PostgresDaoFactory();
        return instance;
    }

    public abstract UserDAO getUserDao();
}