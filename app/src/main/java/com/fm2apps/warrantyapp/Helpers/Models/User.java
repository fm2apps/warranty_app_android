package com.fm2apps.warrantyapp.Helpers.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by heat on 11/12/2017.
 */

@IgnoreExtraProperties
public class User {

    public String Id;
    public String name;
    public String email;
    public String mobilenumber;
    public String password;
    public String countryId;
    public String gender;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, String mobilenumber, String Id, String password, String countryId
    ,String gender) {
        this.Id = Id;
        this.name = name;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.countryId = countryId;
        this.gender = gender;
    }
}
