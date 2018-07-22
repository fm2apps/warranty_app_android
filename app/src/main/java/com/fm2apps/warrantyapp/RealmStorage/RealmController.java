package com.fm2apps.warrantyapp.RealmStorage;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;

import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by heat on 11/8/2017.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public RealmController(Service service) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController with(Service service) {

        if (instance == null) {
            instance = new RealmController(service);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(ProductModel.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<ProductModel> getProducts() {

        return realm.where(ProductModel.class).findAll();
    }

    //query a single item with the given id
    public ProductModel getProduct(String id) {

        return realm.where(ProductModel.class).equalTo("Id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean hasBooks() {

        return !realm.allObjects(ProductModel.class).isEmpty();
    }

    //query example
    public RealmResults<ProductModel> queryedBooks() {

        return realm.where(ProductModel.class)
                .contains("Name", "Author 0")
                .or()
                .contains("BarCode", "Realm")
                .findAll();

    }
}
