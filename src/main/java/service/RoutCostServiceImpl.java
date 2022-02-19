package service;

import model.RoutCost;

import java.util.ArrayList;
import java.util.Collection;

public class RoutCostServiceImpl implements RoutCostService {


    @Override
    public Collection<RoutCost> getAllRoutCosts() {


        Collection<RoutCost> out = new ArrayList<>(); //serviceDistanace.getAllIdDestination();

        for (RoutCost routCost : out) {

          //  routCost.setDistance(serviceDistanace.getDistanceById(routCost.getIdDestination()));
            // routCost.setTariffCosts(serviceTariffCost(getAllTariffsByIdDestination(routCost.getIdDestination())));
            // c.setOptions(optionService.getOptionsByCarId(c.getId()));

        }
        return out;
    }
}

