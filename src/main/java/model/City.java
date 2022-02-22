package model;

import java.util.Objects;

public class City {
    private int idCity;
    private String cityNameEn;
    private String cityNameUkr;

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCityNameUkr() {
        return cityNameUkr;
    }

    public void setCityNameUkr(String cityNameUkr) {
        this.cityNameUkr = cityNameUkr;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public static class Builder {
        private City newCity;

        public Builder() {
            newCity = new City();
        }

        public City build() {
            return newCity;
        }

        public City.Builder withId(int idCity) {
            newCity.idCity = idCity;
            return this;
        }

        public City.Builder withNameCityEn(String nameCityEn) {
            newCity.cityNameEn = nameCityEn;
            return this;
        }

        public City.Builder withNameCityUkr(String nameCityUkr) {
            newCity.cityNameUkr = nameCityUkr;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return idCity == city.idCity && Objects.equals(cityNameEn, city.cityNameEn) && Objects.equals(cityNameUkr, city.cityNameUkr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, cityNameEn, cityNameUkr);
    }

    @Override
    public String toString() {
        return "City{" +
                "idCity=" + idCity +
                ", cityNameEn='" + cityNameEn + '\'' +
                ", cityNameUkr='" + cityNameUkr + '\'' +
                '}';
    }
}
