package com.fm2apps.warrantyapp.BaseActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fm2apps.warrantyapp.Applications.onlineApp;
import com.fm2apps.warrantyapp.Services.ReminderService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by heat on 3/10/2017.
 */

public class BaseActivity extends AppCompatActivity {

    //@Inject
    //public retrofitInterface retrofitInterface;
    protected onlineApp warrantyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setBackgroundDrawableResource(R.drawable.vente_bg);
        //((OnlineApp) getApplication()).getNetComponent().inject(BaseActivity.this);


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        warrantyApp = (onlineApp)this.getApplicationContext();

        Intent serviceIntent = new Intent(BaseActivity.this, ReminderService.class);
        startService(serviceIntent);
    }


    protected void onResume() {
        super.onResume();
        warrantyApp.setCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        Activity currActivity = warrantyApp.getCurrentActivity();
        if (this.equals(currActivity))
            warrantyApp.setCurrentActivity(null);
    }
}
