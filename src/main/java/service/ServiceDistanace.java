package service;

import model.Distance;
import model.User;

import java.util.Collection;
import java.util.List;

public interface ServiceDistanace {
    Collection<Distance> getAllDistance();
    Distance getDistanceById(int idDistance, Collection<Distance> list);
    List<Integer> getAllDistanceId();
}
