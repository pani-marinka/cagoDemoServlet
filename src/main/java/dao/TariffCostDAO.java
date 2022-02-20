package dao;


import model.TariffCost;

import java.util.Collection;

public interface TariffCostDAO {
    Collection<TariffCost> getByAllTariffCost();
    Collection<TariffCost> getByAllTariffAny();
 //   List<Integer> getByAllDistanceId();
}
