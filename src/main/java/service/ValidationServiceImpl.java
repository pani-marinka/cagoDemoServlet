package service;

import dao.UserDAO;
import factorySession.DaoMyFactorySession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    UserDAO userDao = DaoMyFactorySession.getInstance().getUserDao();
    UserServiceImpl userService = new UserServiceImpl(userDao);

    @Override
    public boolean stringValidate(String value) {
        //String log = null;
        if (value == null || value.isEmpty()) return false;
        Pattern VALID_REGEX =
                Pattern.compile("^([a-zA-Z])+$", Pattern.CASE_INSENSITIVE);
        //   String regex = "^([A-Za-z]+[_]+[A-Za-z]+[0-9]+@epam.com)";

        Matcher matcher = VALID_REGEX.matcher(value);
        boolean result = matcher.find();
        return result;
    }


    @Override
    public boolean loginValidate(String value) {
        if (value == null || value.isEmpty()) return false;
        Pattern VALID_REGEX =
                Pattern.compile("(^[\\w])*$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_REGEX.matcher(value);
        boolean result = matcher.find();
        return result;
    }


    @Override
    public boolean emailValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        //boolean result = matcher.find();
        return matcher.find();
    }


    @Override
    public boolean passwordValidate(String pass, String pass2) {
        return (pass != null && pass.equals(pass2));
    }


    @Override
    public boolean validateAuthentication(String login, String pass) {
        return false;
    }

    @Override
    public boolean validateRegistration(String login, String pass1, String pass2) {
        return false;
    }
}
