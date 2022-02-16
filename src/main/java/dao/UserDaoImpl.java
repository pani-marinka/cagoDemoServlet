package dao;

import factorySession.ConnectionPool;
import model.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class UserDaoImpl extends ConnectionPool implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());
    //ConnectionPool connectionPool; // = new ConnectionPool();

    @Override
    //public User getUserByLogin(String login) throws DbException {
    public User getUserByLogin(String login) {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.user WHERE login=\'" + login + "\'");
            if (rs.next()) {
                LOG.info("Exepted get User");
                return new User.Builder()
                        .withId(rs.getString("id"))
                        .withLogin(rs.getString("login"))
                        .withPass(rs.getString("pass"))
                        .withName(rs.getString("name"))
                        .withSurname(rs.getString("surname"))
                        .withEmail(rs.getString("email"))
                        .withPhone(rs.getString("phone"))
                        .build();
            } else return null;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            System.out.println("Error get user;");
            e.printStackTrace();
          //  throw new DbException(e);
            return null;
        }
    }

    //public boolean add(User u) throws DbException{
        public boolean add(User u) {
        if (getUserByLogin(u.getLogin()) != null) {
            return false;
        } else {
            int count = 0;
            try (Connection c = getConnection(); Statement st = c.createStatement();) {

                count = st.executeUpdate("INSERT INTO public.user VALUES(\'" + u.getId() + "\', \'" + u.getLogin() + "\', \'"  + u.getPass() + "\', \'" + u.getName() +  "\', \'" + u.getSurname() + "\', \'" + u.getEmail()+ "\')"); //+ "\', " + u.getEmail() + ", \'" + u.getPhone() + "\'");
            } catch (Exception e) {
                System.out.println("Error save user;");
                e.printStackTrace();
              //  throw new DbException(e);
            }
            return count > 0;
        }
    }

    public Collection<User> getAllUsers() {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM public.user");
            Collection<User> out = new ArrayList<>();
            while (rs.next()) {
                out.add(new User.Builder()
                        .withId(rs.getString("id"))
                        .withLogin(rs.getString("login"))
                        .withPass(rs.getString("pass"))
                        .withName(rs.getString("name"))
                        .withSurname(rs.getString("surname"))
                        .withEmail(rs.getString("email"))
                        .withPhone(rs.getString("phone"))
                        .build());

            }
            return out;
        } catch (Exception e) {
            System.out.println("Error get all users;");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String  getByLoginString(String login) {
        try (Connection c = getConnection(); Statement st = c.createStatement();) {
            ResultSet rs = st.executeQuery("select login FROM public.user WHERE login=\'" + login + "\'");
            //        ResultSet rs = st.executeQuery("SELECT * FROM public.user WHERE login=\'"+login+"\'");
            String out = null;
            while (rs.next()) {
                out = rs.getString("login");
            }
            return out;
        } catch (Exception e) {
            System.out.println("Error get login users;");
            e.printStackTrace();
            return null;
        }

        //return out;
    }


}
