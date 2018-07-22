package com.fm2apps.warrantyapp.Helpers;

import android.net.Uri;

import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.Helpers.Models.User;

/**
 * Created by mohamed on 3/22/2017.
 */

public class Globals {
    //public static String coreUrl = "http://testcase2.mgendy.com/public/";
    public static String coreUrl = "http://onlinestore.mgendy.com/";
    public static String coreImageUrl = "http://onlinestore.devmastersolutions.com/";

    public static int CAPTURE_IMAGE_PHOTO_REQUEST_CODE = 150;
    public static int ATTATCH_IMAGE_PHOTO_REQUEST_CODE = 160;
    public static int CAPTURE_RECEIPT_PHOTO_REQUEST_CODE = 170;
    public static int ATTATCH_RECEIPT_PHOTO_REQUEST_CODE = 180;
    public static String PHOTO1_NAME = "PHOTO1_NAME";
    public static String FORM_TYPE = "1";
    public static Uri imageUri;
    public static String imagePath;
    public static ProductModel productModel;
    public static User loggedUser;
}
