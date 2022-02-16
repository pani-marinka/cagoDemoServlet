package service;

public interface ValidationService {
    boolean stringValidate(String value);
    boolean loginValidate(String value);
    boolean emailValidate(String email);
    boolean passwordValidate(String pass, String pass2);
    boolean validateAuthentication(String login, String pass);
    boolean validateRegistration(String login, String pass1, String pass2);
}
