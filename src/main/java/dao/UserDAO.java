package dao;


import model.User;

public interface UserDAO {
    User getUserByLogin(String login) ;
    boolean add(User u) ;
    String getByLoginString(String login);
}
