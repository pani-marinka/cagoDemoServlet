package service;

import dao.UserDAO;

import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService  {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());
    //UserDAO uDao = DaoFactory.getInstance().getUserDao();
    private final UserDAO uDao;

    public UserServiceImpl(UserDAO uDao) {
        this.uDao = uDao;
    }


    public User getUser(String login)  {
        return uDao.getUserByLogin(login);
    }


    public String getByLoginStr(String login) {
        String   u = uDao.getByLoginString(login);
        return  u;
    }


    @Override
    public User addNewUser(String login, String pass, String name, String lastName, String email) {
        User u = new User.Builder()
                .withId(UUID.randomUUID().toString())
                .withName(name)
                .withSurname(lastName)
                .withLogin(login)
                .withPass(md5Apache(pass))
                .withEmail(email)
                .build();
        //new User(new Random().nextInt(), login, pass, 0, null);
        return uDao.add(u) ? u : null;
    }

    public String md5Apache(String pass) {
        //String md5Hex = DigestUtils.md5DigestAsHex(pass.getBytes(StandardCharsets.UTF_8));
        String md5Hex = DigestUtils.md5Hex(pass.getBytes(StandardCharsets.UTF_8));
        return md5Hex;
    }

    public boolean checkUserPassword(User u, String pass) {
        return u != null && u.getPass().equals(md5Apache(pass));
    }

    public User getByLogin(String login) {
        return uDao.getUserByLogin(login);
    }
}
/*
 public  String md5Apache(String pass){
        String md5Hex = DigestUtils.md5DigestAsHex(pass.getBytes(StandardCharsets.UTF_8));
        return md5Hex;}

    public void addNewUser(String login, String pass, String name,String lastname, int raceId, String email) {
        User u = new User( );
        u.setId(UUID.randomUUID().toString());
        u.setLogin(login);
        u.setPass(md5Apache(pass));
        u.setName(name);
        u.setLastname(lastname);
        u.setRaceid(raceId);
        u.setEmail(email);
        u.setLvl(0);
        u.setPoints(0);
        u.setMoney(0);
        u.setGold(0);
        u.setCreationdate(new Date());
        u.setDeckdate(new Date());
        userDao.add(u);
    }

 */