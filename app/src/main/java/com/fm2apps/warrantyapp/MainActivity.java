package com.fm2apps.warrantyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.R;

public class MainActivity extends BasePresenterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
