package dao;

import factorySession.ConnectionPool;
import model.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class CityDaoImpl extends ConnectionPool implements CityDAO {
    private static final Logger LOG = Logger.getLogger(CityDaoImpl.class.getName());

    @Override
    public City getById(int id) {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.city WHERE idcity=" + id);
            if (rs.next()) {
                LOG.info("Exepted get byIdCity");
                return new City.Builder()
                        .withId(rs.getInt("idcity"))
                        .withNameCityEn(rs.getString("citynameen"))
                        .withNameCityUkr(rs.getString("citynameukr"))
                        .build();
            } else return null;
        } catch (Exception e) {
            LOG.info(e.getMessage());
           // System.out.println("Error get cityById;");
            e.printStackTrace();
            //  throw new DbException(e);
            return null;
        }
    }

    @Override
    public Collection<City> get() {

        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.city");
            Collection<City> out = new ArrayList<>();
            while (rs.next()) {
                LOG.info("Exepted get String");
                out.add(new City.Builder()
                        .withId(rs.getInt("idcity"))
                        .withNameCityEn(rs.getString("citynameen"))
                        .withNameCityUkr(rs.getString("citynameukr"))
                        .build());
            }
            return out;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            //System.out.println("Error get all String;");
            e.printStackTrace();
            //  throw new DbException(e);
            return null;
        }
    }
}

