package com.fm2apps.warrantyapp.BaseActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.fm2apps.warrantyapp.Applications.onlineApp;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.MyProductsActivity;
import com.fm2apps.warrantyapp.NotificationsActivity;
import com.fm2apps.warrantyapp.R;
import com.fm2apps.warrantyapp.Services.ReminderService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by heat on 3/28/2017.
 */

public class BasePresenterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DatabaseReference users_ref;
    public DatabaseReference products_ref;
    public DatabaseReference notifications_ref;
    public FirebaseDatabase dbContext;
    public String TAG = "warranty_db";
    //@Inject
    //public retrofitInterface retrofitInterface;
    protected onlineApp warrantyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg1);

        //((OnlineApp) getApplication()).getNetComponent().inject(BasePresenterActivity.this);

        dbContext = FirebaseDatabase.getInstance();
        users_ref = dbContext.getReference("users");
        products_ref = dbContext.getReference("products");
        notifications_ref = dbContext.getReference("notifications");

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        warrantyApp = (onlineApp)this.getApplicationContext();
        Intent serviceIntent = new Intent(BasePresenterActivity.this, ReminderService.class);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Integer Id = item.getItemId();
        switch (Id)
        {
            case R.id.nav_menu_addproduct:
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.setCancelable(true);
                settingsDialog.show(getSupportFragmentManager(), "selection Dialog");
                break;
            case R.id.nav_menu_products:
                Intent intent = new Intent(BasePresenterActivity.this, MyProductsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_menu_notifications:
                intent = new Intent(BasePresenterActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
        }
        item.setChecked(true);
        //iConnector.notifyMenuClick(Id);
        return false;
    }

}
