package service;

import model.User;

public interface UserService {
    public User addNewUser(String login, String pass, String name, String lastName, String email);

    public User getByLogin(String login);

    public String getByLoginStr(String login);

    public boolean checkUserPassword(User user, String password);
}
