package model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String login;
    private String pass;
    private String email;
    private String phone;
    private int language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public static class Builder{
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withId(String id){
            newUser.id = id;
            return this;
        }

        public Builder withName(String name){
            newUser.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            newUser.surname = surname;
            return this;
        }

        public Builder withLogin(String login){
            newUser.login = login;
            return this;
        }

        public Builder withPass(String pass){
            newUser.pass = pass;
            return this;
        }

        public Builder withEmail(String email){
            newUser.email = email;
            return this;
        }

        public Builder withPhone(String phone){
            newUser.phone = phone;
            return this;
        }

        public Builder withLanguage(int language){
            newUser.language = language;
            return this;
        }

        public User build(){
            return newUser;
        }

    }

}
