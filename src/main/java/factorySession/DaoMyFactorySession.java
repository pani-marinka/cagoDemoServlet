package factorySession;


import dao.CityDAO;
import dao.DistanceDAO;
import dao.TariffCostDAO;
import dao.UserDAO;

public abstract  class DaoMyFactorySession {
    private static DaoMyFactorySession instance = null; //new postgresDaoFactory();

    public static DaoMyFactorySession getInstance() {
        if (instance==null)
            instance = new PostgresDaoFactory();
        return instance;
    }

    public abstract UserDAO getUserDao();
    public abstract DistanceDAO getDistanceDao();
    public abstract TariffCostDAO getTariffCostDao();
    public abstract CityDAO getCityDao();
}