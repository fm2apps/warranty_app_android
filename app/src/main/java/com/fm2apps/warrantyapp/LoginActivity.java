package com.fm2apps.warrantyapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.Helpers.Models.User;
import com.fm2apps.warrantyapp.Helpers.Utilities;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends BasePresenterActivity {

    SweetAlertDialog sweetAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView name_txt = (TextView)findViewById(R.id.name_txt);
        final TextView pass_txt = (TextView)findViewById(R.id.password_txt);

//        Utilities.setActionBar(this);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Button button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        Button button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_txt.getText().toString().isEmpty())
                {
                    name_txt.setError(getResources().getString(R.string.required));
                    name_txt.setFocusable(true);
                    return;
                }

                if (pass_txt.getText().toString().isEmpty())
                {
                    pass_txt.setError(getResources().getString(R.string.required));
                    pass_txt.setFocusable(true);
                    return;
                }
                sweetAlertDialog = Utilities.showProgressPrettyDialog(LoginActivity.this, "Processing");
                sweetAlertDialog.show();
                Query queryRef = users_ref.orderByKey();
                queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Boolean chk = false;
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            User user = singleSnapshot.getValue(User.class);
                            if ((user.name.equals(name_txt.getText().toString())
                                    || user.mobilenumber.equals(name_txt.getText().toString()))
                                    && user.password.equals(pass_txt.getText().toString()))
                            {
                                Globals.loggedUser = user;
                                chk = true;
                            }
                        }
                        sweetAlertDialog.hide();

                        if (!chk)
                        {
                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(LoginActivity.this, MyProductsActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
