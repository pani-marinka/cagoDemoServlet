package service;

import model.RoutCost;
import model.TariffCost;

import java.util.Collection;

public interface RoutCostService {
    Collection<RoutCost> getAllRoutCosts();

    Collection<RoutCost> getRoutsByCityFrom(String cityNameFrom);

    Collection<RoutCost> getRoutsByCityTo(String cityNameTo);
}
