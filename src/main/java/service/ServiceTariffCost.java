package service;

import model.Distance;
import model.TariffCost;

import java.util.Collection;

public interface ServiceTariffCost {
    Collection<TariffCost> getAllTariffCost();
    Collection<TariffCost> getDistanceById(int idDistance, Collection<TariffCost> list);
    Collection<TariffCost> getTariffAny();
}
