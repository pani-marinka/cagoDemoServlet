package service;

import model.User;

public interface UserService {
public User addNewUser(String login,String pass);
public User getByLogin(String login);
public String getByLoginStr(String login);
        }
