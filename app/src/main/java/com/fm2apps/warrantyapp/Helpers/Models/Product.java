package com.fm2apps.warrantyapp.Helpers.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by heat on 11/15/2017.
 */

@IgnoreExtraProperties
public class Product {
    public String Id;
    public String barCode;
    public Date expiryDate;
    public String name;
    public String desc;
    public String img64;
    public String receipt64;
    public String userId;
    public Date dateCreated;

    public Product() {
    }

    public Product(String barCode, Date expiryDate, String name, String Id,
                   String desc, String img64, String receipt64, Date dateCreated, String userId) {
        this.Id = Id;
        this.barCode = barCode;
        this.expiryDate = expiryDate;
        this.name = name;
        this.desc = desc;
        this.img64 = img64;
        this.receipt64 = receipt64;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }
}
