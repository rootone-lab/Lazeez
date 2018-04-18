package com.rootonelabs.vicky.lazeez.Model;

/**
 * Created by Percy on 12/18/2017.
 */

public class User {


    private String name;
    private String password;
    private String Phone;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String setname(String Pname) {
        name = Pname;
        return name;
    }

    public String getName() {
        return name;
    }

    public String getpassword() {
        return password;
    }
}