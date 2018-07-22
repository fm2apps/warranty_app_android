package com.fm2apps.warrantyapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fm2apps.warrantyapp.Adapters.CountriesAdapter;
import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.CountryModel;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.fm2apps.warrantyapp.Helpers.Utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fm2apps.warrantyapp.Helpers.Models.User;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends BasePresenterActivity {
//    private DatabaseReference users_ref;
//    private FirebaseDatabase mFirebaseInstance;
//    String TAG = "db1";
    SweetAlertDialog sweetAlertDialog;
    List<CountryModel> countryModels;
    CountryModel countryModel;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Utilities.getPermissions(this);
        //Utilities.setActionBar(this);

        //FirebaseCrash.report(new Exception("App Name : My first Android non-fatal error"));

//        mFirebaseInstance = FirebaseDatabase.getInstance();
//        users_ref = mFirebaseInstance.getReference("users");

//        String userId = mFirebaseDatabase.push().getKey();
//        User user = new User("wqwqqw 2ew11eew", "ss2333s@sssss.iynfo");
//        mFirebaseDatabase.child(userId).setValue(user);

        // Read from the database
//        users_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                //String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + "");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

//        String userId = mFirebaseDatabase.push().getKey();
//        User user = new User("wqwqqw 2eweew", "ss2s@sssss.info");
//        mFirebaseDatabase.child(userId).setValue(user);

//        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                User user = dataSnapshot.getValue(User.class);
//                Log.d(TAG, "User name: " + user.name + ", email " + user.email);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

//        DatabaseReference myRef = mFirebaseInstance.getReference().child("users");// Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            // This method is called once with the initial value and again
//                                            // whenever data at this location is updated.
//                                            Log.i("Info", "Data changed" + Long.toString(dataSnapshot.getChildrenCount()));
//
//                                            for (DataSnapshot dis : dataSnapshot.getChildren()) {
//                                                for (DataSnapshot vers : dis.getChildren()) {
//                                                    String value = vers.getValue(String.class);
//                                                    Log.d("debug", "Value is: " + value);
//                                                    //toaster(value);
//                                                }
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(DatabaseError databaseError) {
//
//                                        }
//                                    });



        //Query queryRef = mFirebaseDatabase.orderByChild("name");
//        queryRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<String> cities = new ArrayList<String>();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    cities.add(postSnapshot.getValue().toString());
//                }}
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//            queryRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String ss = s;
//                //Do something with the individual node here`enter code here`
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                String ss = s;
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                String ss = s;
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        mFirebaseDatabase.child("users");
//        mFirebaseDatabase.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });




//        final Realm realm = Realm.getInstance(this);
//        ProductModel productModel = new ProductModel();
//        productModel.setId(2);
//        productModel.setName("gendy Phone2");
//        productModel.setDesc("gendy Phone121231121212");
//        productModel.setDateCreated(Calendar.getInstance().getTime());
//        productModel.setImage64("");
//        productModel.setBarCode("58877224559");
//        productModel.setReceipt64("");
//        Date date = Calendar.getInstance().getTime();
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.HOUR, 1);
//        productModel.setExpiryDate(c.getTime());
//        realm.beginTransaction();
//        realm.copyToRealm(productModel);
//        realm.commitTransaction();

//
//        RealmResults<ProductModel> realmResults = RealmController.with(RegisterActivity.this).getProducts();
//        ProductModel b = realmResults.get(0);
//        String title = b.getName();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
//        String date = df.format(b.getDateCreated());

//        Query queryRef = users_ref.orderByKey();
//        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//                    User user = singleSnapshot.getValue(User.class);
//                    String ss = "";
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e(TAG, "onCancelled", databaseError.toException());
//            }
//        });

        final TextView email_txt = (TextView)findViewById(R.id.email_txt);
        final TextView name_txt = (TextView)findViewById(R.id.name_txt);
        final TextView number_txt = (TextView)findViewById(R.id.number_txt);
        final TextView password_txt = (TextView)findViewById(R.id.password_txt);
        final Spinner country_spinner = (Spinner)findViewById(R.id.country_spinner);
        final ImageView country_iv = (ImageView)findViewById(R.id.country_iv);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);

        countryModels = new ArrayList<>();
        CountryModel model1 = new CountryModel();
        model1.Id = "1";
        model1.Name = "Egypt";
        countryModels.add(model1);
        model1 = new CountryModel();
        model1.Id = "2";
        model1.Name = "UAE";
        countryModels.add(model1);
        countryModel = countryModels.get(0);


        CountriesAdapter countriesAdapter = new CountriesAdapter(RegisterActivity.this, R.layout.spinner_item, countryModels);
        country_spinner.setAdapter(countriesAdapter);
        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryModel = countryModels.get(position);
                if (countryModel.Id.equals("1"))
                {
                    country_iv.setImageDrawable(getResources().getDrawable(R.drawable.egypt));
                }
                else if(countryModel.Id.equals("2"))
                {
                    country_iv.setImageDrawable(getResources().getDrawable(R.drawable.uae));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Button button_register = (Button)findViewById(R.id.button_register);
        Button button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name_txt.getText().toString().isEmpty())
                {
                    name_txt.setError(getResources().getString(R.string.required));
                    name_txt.setFocusable(true);
                    return;
                }

                if (number_txt.getText().toString().isEmpty())
                {
                    number_txt.setError(getResources().getString(R.string.required));
                    number_txt.setFocusable(true);
                    return;
                }

                if (email_txt.getText().toString().isEmpty())
                {
                    email_txt.setError(getResources().getString(R.string.required));
                    email_txt.setFocusable(true);
                    return;
                }

                if (password_txt.getText().toString().isEmpty())
                {
                    password_txt.setError(getResources().getString(R.string.required));
                    password_txt.setFocusable(true);
                    return;
                }

                if (countryModel == null)
                {
                    Toast.makeText(getApplicationContext(), "Choose Country", Toast.LENGTH_LONG).show();
                    return;
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);


                sweetAlertDialog = Utilities.showProgressPrettyDialog(RegisterActivity.this, "Processing");
                sweetAlertDialog.show();
                Query queryRef = users_ref.orderByKey();
                queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Boolean chk = false;
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            User user = singleSnapshot.getValue(User.class);
                            if (user.email.equals(email_txt.getText().toString())
                                    || user.mobilenumber.equals(number_txt.getText().toString())
                                    || user.name.equals(name_txt.getText().toString()))
                            {
                                chk = true;
                            }
                        }
                        sweetAlertDialog.hide();

                        if (chk)
                        {
                            Toast.makeText(getApplicationContext(), "User Exists", Toast.LENGTH_LONG).show();
                            return;
                        }

                        String userId = users_ref.push().getKey();
                        User user = new User(name_txt.getText().toString(), email_txt.getText().toString(),
                                number_txt.getText().toString(), userId, password_txt.getText().toString()
                        , countryModel.Id, radioButton.getText().toString().equals("Male") ? "1" : "2");
                        Globals.loggedUser = user;
                        users_ref.child(userId).setValue(user);

                        SettingsDialog settingsDialog = new SettingsDialog();
                        settingsDialog.setCancelable(true);
                        settingsDialog.show(getSupportFragmentManager(), "selection Dialog");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });

//
//
//                ProductModel productModel = new ProductModel();
//                productModel.setId(2);
//                productModel.setName("gendy Phone2");
//                productModel.setDesc("gendy Phone121231121212");
//                productModel.setDateCreated(Calendar.getInstance().getTime());
//                productModel.setImage64("");
//                productModel.setBarCode("58877224559");
//                productModel.setReceipt64("");
//
//                realm.beginTransaction();
//                realm.copyToRealm(productModel);
//                realm.commitTransaction();


                //String ss = "";

//                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(RegisterActivity.this, CheckCodeActivity.class);
//                startActivity(intent);
            }
        });
    }

//    private void collectPhoneNumbers(Map<String,Object> users) {
//
//        ArrayList<Long> phoneNumbers = new ArrayList<>();
//
//        //iterate through each user, ignoring their UID
//        for (Map.Entry<String, Object> entry : users.entrySet()){
//
//            //Get user map
//            Map singleUser = (Map) entry.getValue();
//            //Get phone field and append to list
//            phoneNumbers.add((Long) singleUser.get("phone"));
//        }
//
//        System.out.println(phoneNumbers.toString());
//    }

    //String Photo64;
    String Receipt64;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Globals.CAPTURE_RECEIPT_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Uri pickedImage = data.getData();
                String imagePath = Utilities.getPath(RegisterActivity.this, pickedImage);
                File gallery_picFile = new File(imagePath);
                Bitmap photo = Utilities.decodeFile(gallery_picFile);
                int mFactor = Utilities.getResizedImageFactor(photo.getWidth());
                photo = Bitmap.createScaledBitmap(photo, photo.getWidth() / mFactor, photo.getHeight() / mFactor, false);
                ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 20, bmpStream);
                photo = Utilities.rotateMyPhoto(gallery_picFile, photo);

                String tobase64 = Utilities.EncodeTobase64(photo);
                //iv_pick.setImageBitmap(photo);
                Receipt64 = tobase64;
                if(Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setReceipt64(Receipt64);
                Toast.makeText(getApplicationContext(), "Selected Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, Step1Activity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else if (requestCode == Globals.ATTATCH_RECEIPT_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Bitmap photo = null;
                File gallery_picFile = null;
                ContentResolver cr = getContentResolver();

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {

                    gallery_picFile = new File(Environment.getExternalStorageDirectory(), Globals.imagePath + ".jpg");
                    photo = android.provider.MediaStore.Images.Media
                            .getBitmap(cr, Globals.imageUri);
                } else {
                    gallery_picFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Globals.imagePath + ".jpg");
                    photo = android.provider.MediaStore.Images.Media
                            .getBitmap(cr, Globals.imageUri);

                }

                ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 20, bmpStream);
                int mFactor = Utilities.getResizedImageFactor(photo.getWidth());
                photo = Bitmap.createScaledBitmap(photo, photo.getWidth() / mFactor, photo.getHeight() / mFactor, false);
                photo = Utilities.rotateMyPhoto(gallery_picFile, photo);
                String tobase64 = Utilities.EncodeTobase64(photo);
                //iv_pick.setImageBitmap(photo);
                Receipt64 = tobase64;
                if(Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setReceipt64(Receipt64);
                Toast.makeText(getApplicationContext(), "Selected Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, Step1Activity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
