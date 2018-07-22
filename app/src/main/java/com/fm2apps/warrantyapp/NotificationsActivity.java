package com.fm2apps.warrantyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Fragments.NotificationsFragment;
import com.fm2apps.warrantyapp.Fragments.ProductsFragment;
import com.fm2apps.warrantyapp.Helpers.Utilities;

public class NotificationsActivity extends BasePresenterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        try
        {
            setContentView(R.layout.activity_notifications);
            NotificationsFragment notificationsFragment = new NotificationsFragment();
            getFragmentManager().beginTransaction().add(R.id.container, notificationsFragment).commit();
            Utilities.setActionBar(this);
        }
        catch (Exception ex){
        }
    }
}
