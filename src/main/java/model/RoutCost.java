package model;

import java.util.Collection;

public class RoutCost {
    private int idDestination;
    //private Distance distance;
    private Collection<Distance> distanceCollection;
    private Collection<TariffCost> tariffCosts;

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

//    public Distance getDistance() {
//        return distance;
//    }
//
//    public void setDistance(Distance distance) {
//        this.distance = distance;
//    }

    public Collection<TariffCost> getTariffCosts() {
        return tariffCosts;
    }

    public void setTariffCosts(Collection<TariffCost> tariffCosts) {
        this.tariffCosts = tariffCosts;
    }

    public Collection<Distance> getDistanceCollection() {
        return distanceCollection;
    }

    public void setDistanceCollection(Collection<Distance> distanceCollection) {
        this.distanceCollection = distanceCollection;
    }

    public static class Builder {
        private RoutCost newRoutCost;

        public Builder() {
            newRoutCost = new RoutCost();
        }

        public RoutCost build() {
            return newRoutCost;
        }

        public RoutCost.Builder withId(int idDestination) {
            newRoutCost.idDestination = idDestination;
            return this;
        }
//        public RoutCost.Builder withDistance(Distance distance) {
//            newRoutCost.distance = distance;
//            return this;
//        }
        public RoutCost.Builder withCollectionTariffCost(Collection<TariffCost> tariffCosts) {
            newRoutCost.tariffCosts = tariffCosts;
            return this;
        }

        public RoutCost.Builder withCollectionDistance(Collection<Distance> distanceCollection) {
            newRoutCost.distanceCollection = distanceCollection;
            return this;
        }
    }
}
