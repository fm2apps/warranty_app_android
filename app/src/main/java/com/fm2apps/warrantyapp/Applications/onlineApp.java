package com.fm2apps.warrantyapp.Applications;

import android.app.Activity;
import android.app.Application;

/**
 * Created by mohamed on 11/15/2017.
 */

public class onlineApp extends Application {
    protected onlineApp warrantyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        warrantyApp = (onlineApp)this.getApplicationContext();
    }

    private Activity mCurrentActivity = null;

    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }
}
