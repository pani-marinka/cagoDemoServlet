package dao;

import model.City;

import java.util.Collection;

public interface CityDAO {
    City getById(int id);
    Collection<City> get();
}
