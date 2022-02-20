package service;

import model.City;

import java.util.Collection;

public interface CityService {
    Collection<City> getAllCity();
    City getCityById(int idCity);
    City getCityByIdFromList(Collection<City> collectionCities, int idCity);
}
