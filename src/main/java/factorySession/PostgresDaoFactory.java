package factorySession;

import dao.*;

public class PostgresDaoFactory extends DaoMyFactorySession {
    private static UserDAO instance = null; //new UserDaoImpl();
    private static DistanceDAO instanceDistance = null;
    private static TariffCostDAO instanceTariffCost = null;
    private static CityDAO instanceCity = null;

    @Override
    public UserDAO getUserDao() {
        if (instance == null)
            instance = new UserDaoImpl();
        return instance;
    }

    @Override
    public DistanceDAO getDistanceDao() {
        if (instanceDistance == null)
            instanceDistance = new DistanceDAOImpl();
        return instanceDistance;
    }

    @Override
    public TariffCostDAO getTariffCostDao() {
        if (instanceTariffCost == null)
            instanceTariffCost = new TariffCostDAOImpl();
        return instanceTariffCost;
    }

    @Override
    public CityDAO getCityDao() {
        if (instanceCity == null)
            instanceCity = new CityDaoImpl();
        return instanceCity;
    }
}
