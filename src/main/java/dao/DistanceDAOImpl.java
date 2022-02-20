package dao;

import factorySession.ConnectionPool;
import model.Distance;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class DistanceDAOImpl extends ConnectionPool implements DistanceDAO {
    private static final Logger LOG = Logger.getLogger(DistanceDAOImpl.class.getName());

    @Override
    public Collection<Distance> getByAllDistance() {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.distance");
            Collection<Distance> out = new ArrayList<>();
            while (rs.next()) {
                LOG.info("Exepted get Distance");
                out.add(new Distance.Builder()
                        .withId(rs.getInt("iddestination"))
                        .withCityFromId(rs.getInt("city_from"))
                        .withCityToId(rs.getInt("city_to"))
                        .withDistance(rs.getDouble("distance"))
                        .build());
            }
            return out;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            System.out.println("Error get all Distance;");
            e.printStackTrace();
            //  throw new DbException(e);
            return null;
        }
    }


}
