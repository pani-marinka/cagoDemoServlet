package service;

import dao.DistanceDAO;
import dao.TariffCostDAO;
import enums.TonnageDelivery;
import model.Distance;
import model.TariffCost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class ServiceTariffCostImpl implements ServiceTariffCost {
    private static final Logger LOG = Logger.getLogger(ServiceDistanaceImpl.class.getName());

    private final TariffCostDAO tariffCostDAO;

    public ServiceTariffCostImpl(TariffCostDAO tariffCostDAO) {
        this.tariffCostDAO = tariffCostDAO;
    }

    @Override
    public Collection<TariffCost> getAllTariffCost() {
        return tariffCostDAO.getByAllTariffCost();
    }

    @Override
    public Collection<TariffCost> getDistanceById(int idDistance, Collection<TariffCost> list) {
        Collection<TariffCost> out = new ArrayList<>();
        for (TariffCost tariffCost : list) {
            if (idDistance == tariffCost.getIdDistance()) {
                tariffCost.setTonnageVolume(TonnageDelivery.getTonnageById(tariffCost.getMassId()));
                out.add(tariffCost);
            }
        }
        if (out.isEmpty()) {
            for (TariffCost tariffCost : list) {
                if (tariffCost.getIdDistance() == -1) {
                    tariffCost.setTonnageVolume(TonnageDelivery.getTonnageById(tariffCost.getMassId()));
                    out.add(tariffCost);
                }
            }
        }
        return out;
    }

    @Override
    public Collection<TariffCost> getTariffAny() {
        Collection<TariffCost> out = tariffCostDAO.getByAllTariffAny();
        for (TariffCost tariffCost : out) {
            tariffCost.setTonnageVolume(TonnageDelivery.getTonnageById(tariffCost.getMassId()));
        }
        return out;
    }
}
