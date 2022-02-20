package service;

import dao.CityDAO;
import model.City;

import java.util.Collection;
import java.util.logging.Logger;

public class CityServiceImpl implements CityService {
    private static final Logger LOG = Logger.getLogger(CityServiceImpl.class.getName());

    private final CityDAO cityDao;

    public CityServiceImpl(CityDAO cityDao) {
        this.cityDao = cityDao;
    }
    @Override
    public Collection<City> getAllCity() {
        return cityDao.get();
    }

    @Override
    public City getCityById(int idCity) {
        return getCityById(idCity);
    }

    @Override
    public City getCityByIdFromList(Collection<City> collectionCities, int idCity) {
        return collectionCities.stream()
                .filter(i -> idCity == (i.getIdCity()))
                .findAny()
                .orElse(null);
            }
}
