package com.rootonelabs.vicky.lazeez.Model;

/**
 * Created by Percy on 12/18/2017.
 */

public class User {


    private String name;
    private String password;

    public User() {
    }

    public User(String Pname, String Ppassword) {


        name = Pname;
        password = Ppassword;
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