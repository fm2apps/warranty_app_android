package com.fm2apps.warrantyapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.Fragments.ProductsFragment;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.Helpers.Utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.fm2apps.warrantyapp.R.id.add_iv;

public class MyProductsActivity extends BasePresenterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.activity_my_products);
            ProductsFragment productsFragment = new ProductsFragment();
            getFragmentManager().beginTransaction().add(R.id.container, productsFragment).commit();
            Utilities.setActionBar(this);
        }
        catch (Exception ex){
        }
    }

    private View getActionBarView() {

        int actionViewResId = 0;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            actionViewResId = getResources().getIdentifier(
                    "abs__action_bar_container", "id", getPackageName());
        } else {
            actionViewResId = Resources.getSystem().getIdentifier(
                    "action_bar_container", "id", "android");
        }
        if (actionViewResId > 0) {
            return this.findViewById(actionViewResId);
        }

        return null;
    }

    String Receipt64;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Globals.CAPTURE_RECEIPT_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Uri pickedImage = data.getData();
                String imagePath = Utilities.getPath(MyProductsActivity.this, pickedImage);
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
                Intent intent = new Intent(MyProductsActivity.this, Step1Activity.class);
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
                Intent intent = new Intent(MyProductsActivity.this, Step1Activity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
