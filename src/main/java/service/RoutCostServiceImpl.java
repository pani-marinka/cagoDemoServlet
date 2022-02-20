package service;

import dao.DistanceDAO;
import dao.TariffCostDAO;
import factorySession.DaoMyFactorySession;
import model.Distance;
import model.RoutCost;
import model.TariffCost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RoutCostServiceImpl implements RoutCostService {

    private static final Logger LOG = Logger.getLogger(RoutCostServiceImpl.class.getName());

    DistanceDAO distanceDao = DaoMyFactorySession.getInstance().getDistanceDao();
    TariffCostDAO tariffCostDao = DaoMyFactorySession.getInstance().getTariffCostDao();

    public RoutCostServiceImpl(DistanceDAO distanceDao, TariffCostDAO tariffCostDao) {
        this.distanceDao = distanceDao;
        this.tariffCostDao = tariffCostDao;
    }

    ServiceDistanace serviceDistance = new ServiceDistanaceImpl(distanceDao);
    ServiceTariffCost serviceTariffCost = new ServiceTariffCostImpl(tariffCostDao);


    @Override
    public Collection<RoutCost> getAllRoutCosts() {

        Collection<Distance> destinations = serviceDistance.getAllDistance();
        Collection<TariffCost> tariffCosts = serviceTariffCost.getAllTariffCost();
        Collection<Distance> distanceOne = new ArrayList<>();


        List<Integer> idDestination = destinations.stream()
                .map(i -> i.getIdDestination())
                .collect(Collectors.toList());

        Collection<RoutCost> out = new ArrayList<>();

        for (int id : idDestination) {
            out.add(new RoutCost.Builder()
                    .withId(id)
                    .build());
        }

        for (RoutCost routCost : out) {
            distanceOne.add(serviceDistance.getDistanceById(routCost.getIdDestination(), destinations));
            routCost.setDistanceCollection(distanceOne);
            routCost.setTariffCosts(serviceTariffCost.getDistanceById(routCost.getIdDestination(), tariffCosts));
            distanceOne = new ArrayList<>();
        }
/*
 public Collection<Ad> getAdsByCity(String cityId) {
        Collection<Ad> out = adDao.getByCity(cityId);
        return out;
    }
 */

        return out;
    }

    @Override
    public Collection<RoutCost> getRoutsByCityFrom(String cityNameFrom) {
        Collection<RoutCost>  out = new ArrayList<>();
        Collection<Distance> distanceThis = new ArrayList<>();
        Collection<RoutCost>  allRouts = getAllRoutCosts();//adDao.getByCity(cityId);
        for(RoutCost routCost:allRouts) {
            distanceThis = routCost.getDistanceCollection();
            for(Distance distance:distanceThis){
                if(distance.getCityFromNameEn().equals(cityNameFrom))
                    out.add(routCost);
            }
        }
        return out;
    }

    @Override
    public Collection<RoutCost> getRoutsByCityTo(String cityNameTo) {
        Collection<RoutCost>  out = new ArrayList<>();
        Collection<Distance> distanceThis = new ArrayList<>();
        Collection<RoutCost>  allRouts = getAllRoutCosts();//adDao.getByCity(cityId);
        for(RoutCost routCost:allRouts) {
            distanceThis = routCost.getDistanceCollection();
            for(Distance distance:distanceThis){
                if(distance.getCityToNameEn().equals(cityNameTo))
                    out.add(routCost);
            }
        }
        return out;
    }
}

