package model;

import java.util.Objects;

public class Distance {
    private int idDestination;
    private int CityFromId;
    String cityFromNameEn;
    String cityFromNameUkr;
    private int cityToId;
    String cityToNameEn;
    String cityToNameUkr;
    private double distance;

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getCityFromId() {
        return CityFromId;
    }

    public void setCityFromId(int cityFromId) {
        CityFromId = cityFromId;
    }

    public String getCityFromNameEn() {
        return cityFromNameEn;
    }

    public void setCityFromNameEn(String cityFromNameEn) {
        this.cityFromNameEn = cityFromNameEn;
    }

    public String getCityFromNameUkr() {
        return cityFromNameUkr;
    }

    public void setCityFromNameUkr(String cityFromNameUkr) {
        this.cityFromNameUkr = cityFromNameUkr;
    }

    public int getCityToId() {
        return cityToId;
    }

    public void setCityToId(int cityToId) {
        this.cityToId = cityToId;
    }

    public String getCityToNameEn() {
        return cityToNameEn;
    }

    public void setCityToNameEn(String cityToNameEn) {
        this.cityToNameEn = cityToNameEn;
    }

    public String getCityToNameUkr() {
        return cityToNameUkr;
    }

    public void setCityToNameUkr(String cityToNameUkr) {
        this.cityToNameUkr = cityToNameUkr;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return idDestination == distance1.idDestination && CityFromId == distance1.CityFromId && cityToId == distance1.cityToId && Double.compare(distance1.distance, distance) == 0 && Objects.equals(cityFromNameEn, distance1.cityFromNameEn) && Objects.equals(cityFromNameUkr, distance1.cityFromNameUkr) && Objects.equals(cityToNameEn, distance1.cityToNameEn) && Objects.equals(cityToNameUkr, distance1.cityToNameUkr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDestination, CityFromId, cityFromNameEn, cityFromNameUkr, cityToId, cityToNameEn, cityToNameUkr, distance);
    }

    public static class Builder {
        private Distance newDistance;

        public Builder() {
            newDistance = new Distance();
        }

        public Distance build() {
            return newDistance;
        }

        public Distance.Builder withId(int idDestination) {
            newDistance.idDestination = idDestination;
            return this;
        }

        public Distance.Builder withCityFromNameEn(String cityFromNameEn) {
            newDistance.cityFromNameEn = cityFromNameEn;
            return this;
        }

        public Distance.Builder withCityFromNameUkr(String cityFromNameUkr) {
            newDistance.cityFromNameEn = cityFromNameUkr;
            return this;
        }

        public Distance.Builder withCityFromId(int CityFromId) {
            newDistance.CityFromId = CityFromId;
            return this;
        }

        public Distance.Builder withCityToNameEn(String cityToNameEn) {
            newDistance.cityToNameEn = cityToNameEn;
            return this;
        }

        public Distance.Builder withCityToNameUkr(String cityToNameUkr) {
            newDistance.cityToNameEn = cityToNameUkr;
            return this;
        }

        public Distance.Builder withCityToId(int cityToId) {
            newDistance.cityToId = cityToId;
            return this;
        }

        public Distance.Builder withDistance(double distance) {
            newDistance.distance = distance;
            return this;
        }
    }
}
