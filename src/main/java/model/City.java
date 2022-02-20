package model;

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
}
