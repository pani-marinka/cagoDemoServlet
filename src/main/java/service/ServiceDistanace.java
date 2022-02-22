package service;

import model.City;
import model.Distance;
import model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ServiceDistanace {
    Collection<Distance> getAllDistance();
    Distance getDistanceById(int idDistance, Collection<Distance> list);
    Set<City> getCitiesRouters();
    List<Integer> getAllDistanceId();
}
