package dao;

import factorySession.ConnectionPool;
import model.TariffCost;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class TariffCostDAOImpl extends ConnectionPool implements TariffCostDAO {
    private static final Logger LOG = Logger.getLogger(TariffCostDAOImpl.class.getName());

    @Override
    public Collection<TariffCost> getByAllTariffCost() {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.tariff_cost");
            Collection<TariffCost> out = new ArrayList<>();
            while (rs.next()) {
                LOG.info("Excepted get TariffCost");
                out.add(new TariffCost.Builder()
                        .withId(rs.getInt("idtariff"))
                        .withIdDistance(rs.getInt("iddistance"))
                        .withMassId(rs.getInt("massid"))
                        .withCost(rs.getDouble("cost"))
                        .build());
            }
            return out;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            System.out.println("Error get all TariffCost;");
            e.printStackTrace();
            //  throw new DbException(e);
            return null;
        }
    }

    @Override
    public Collection<TariffCost> getByAllTariffAny() {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.tariff_cost WHERE iddistance=-1");
            Collection<TariffCost> out = new ArrayList<>();
            while (rs.next()) {
                LOG.info("Excepted get TariffCost");
                out.add(new TariffCost.Builder()
                        .withId(rs.getInt("idtariff"))
                        .withIdDistance(rs.getInt("iddistance"))
                        .withMassId(rs.getInt("massid"))
                        .withCost(rs.getDouble("cost"))
                        .build());
            }
            return out;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            System.out.println("Error get all TariffCost;");
            e.printStackTrace();
            //  throw new DbException(e);
            return null;
        }
    }


}
