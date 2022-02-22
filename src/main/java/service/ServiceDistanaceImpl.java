package service;

import dao.CityDAO;
import dao.DistanceDAO;
import factorySession.DaoMyFactorySession;
import model.City;
import model.Distance;

import java.util.*;
import java.util.logging.Logger;

public class ServiceDistanaceImpl implements ServiceDistanace {
    private static final Logger LOG = Logger.getLogger(ServiceDistanaceImpl.class.getName());

    DistanceDAO distanceDao = DaoMyFactorySession.getInstance().getDistanceDao();
    CityDAO cityDAO = DaoMyFactorySession.getInstance().getCityDao();

    CityService cityService = new CityServiceImpl(cityDAO);

    public ServiceDistanaceImpl(DistanceDAO distanceDao) {
        this.distanceDao = distanceDao;
    }


    @Override
    public Collection<Distance> getAllDistance() {
        Collection<City> cities = cityService.getAllCity();
        City cityFrom = null, cityTo = null;
        Collection<Distance> out = distanceDao.getByAllDistance();
        for (Distance distance : out) {
            cityFrom = cityService.getCityByIdFromList(cities, distance.getCityFromId());
            //System.out.println("CityFromOne " + cityFrom.getCityNameEn());

            cityTo = cityService.getCityByIdFromList(cities, distance.getCityToId());
            distance.setCityFromNameEn(cityFrom.getCityNameEn());
            distance.setCityFromNameUkr(cityFrom.getCityNameUkr());
            distance.setCityToNameEn(cityTo.getCityNameEn());
            distance.setCityToNameUkr(cityTo.getCityNameUkr());
            //  System.out.println("CityFromOneDistance " + distance.getCityFromNameEn());
        }
        return out;
    }

    @Override
    public Set<City> getCitiesRouters() {
        ArrayList<Distance> distances = (ArrayList<Distance>) getAllDistance();
        Set<Integer> idCities = new HashSet<>();
        Set<City> citiesForDelivery = new HashSet<>();
        distances.stream().forEach(i -> citiesForDelivery.add(new City.Builder()
                .withId(i.getCityFromId())
                .withNameCityEn(i.getCityFromNameEn())
                .withNameCityUkr(i.getCityFromNameUkr())
                .build()));
        distances.stream().forEach(i -> citiesForDelivery.add(new City.Builder()
                .withId(i.getCityToId())
                .withNameCityEn(i.getCityToNameEn())
                .withNameCityUkr(i.getCityToNameUkr())
                .build()));
        System.out.println("citiesForDelivery " + citiesForDelivery);
        return citiesForDelivery;
    }

    @Override
    public List<Integer> getAllDistanceId() {
        return null;
    }

    @Override
    public Distance getDistanceById(int idDistance, Collection<Distance> list) {
        Distance distance = new Distance();
        // Collection<Distance> distNew = new ArrayList<>();
        //   System.out.println("idDistance " + idDistance);
        for (Distance distanceList : list) {

            if (idDistance == distanceList.getIdDestination()) {
                return distanceList;
            }
        }

        return distance;
    }
}
