package com.fm2apps.warrantyapp.Helpers.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by heat on 1/9/2018.
 */

@IgnoreExtraProperties
public class Notification {
    public String Id;
    public String productId;
    public String userId;
    public String userName;
    public String productName;
    public Date dateCreated;
    public Date expiryDate;

    public Notification() {
    }

    public Notification(String Id, String productId, String userId, String userName, String productName, Date dateCreated, Date expiryDate) {
        this.Id = Id;
        this.productId = productId;
        this.userId = userId;
        this.userName = userName;
        this.productName = productName;
        this.dateCreated = dateCreated;
        this.expiryDate = expiryDate;
    }
}
