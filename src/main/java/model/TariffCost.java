package model;

import java.util.Collection;

public class TariffCost {
    private int idTariff;
    private int massId;
    private int idDistance;
    private double cost;
    private String tonnageVolume;
  //  private Collection<MassDirectory> massDirectoryCollection;
  //  MassDirectory massDirectory;

    public String getTonnageVolume() {
        return tonnageVolume;
    }

    public void setTonnageVolume(String tonnageVolume) {
        this.tonnageVolume = tonnageVolume;
    }

    public int getIdTariff() {
        return idTariff;
    }

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    public int getMassId() {
        return massId;
    }

    public void setMassId(int massId) {
        this.massId = massId;
    }

    public int getIdDistance() {
        return idDistance;
    }

    public void setIdDistance(int idDistance) {
        this.idDistance = idDistance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public static class Builder {
        private TariffCost newTariffCost;

        public Builder() {
            newTariffCost = new TariffCost();
        }

        public TariffCost build() {
            return newTariffCost;
        }

        public TariffCost.Builder withId(int idTariff) {
            newTariffCost.idTariff = idTariff;
            return this;
        }

        public TariffCost.Builder withIdDistance(int idDistance) {
            newTariffCost.idDistance = idDistance;
            return this;
        }

        public TariffCost.Builder withMassId(int massId) {
            newTariffCost.massId = massId;
            return this;
        }

        public TariffCost.Builder withCost(double cost) {
            newTariffCost.cost = cost;
            return this;
        }

        public TariffCost.Builder withTonnageVolume(String tonnageVolume) {
            newTariffCost.tonnageVolume = tonnageVolume;
            return this;
        }

    }
}
