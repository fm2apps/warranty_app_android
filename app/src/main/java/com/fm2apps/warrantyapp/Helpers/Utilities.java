package com.fm2apps.warrantyapp.Helpers;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.fm2apps.warrantyapp.BaseActivities.BasePresenterActivity;
import com.fm2apps.warrantyapp.Dialogs.SettingsDialog;
import com.fm2apps.warrantyapp.MyProductsActivity;
import com.fm2apps.warrantyapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by heat on 3/12/2017.
 */

public class Utilities {
    private final static int FADE_DURATION = 1000;

    public static String getLanguage(Context context){
        return context.getResources().getConfiguration().locale.toString();
    }

    public static SweetAlertDialog showProgressPrettyDialog(Context context, String message) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(message);
        pDialog.setCancelable(false);
        return pDialog;
    }


    public static View getActionBarView(final Activity activity) {
        if (activity instanceof IToolbarHolder)
            return ((IToolbarHolder) activity).getToolbar();
        final String packageName = activity instanceof ActionBarActivity ? activity.getPackageName() : "android";
        final int resId = activity.getResources().getIdentifier("action_bar_container", "id", packageName);
        final View view = activity.findViewById(resId);
        return view;
    }

    public interface IToolbarHolder {
        public android.support.v7.widget.Toolbar getToolbar();
    }

    public static Menu setActionBar(final BasePresenterActivity v)
    {
        try
        {
            Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
            //Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
            v.setSupportActionBar(toolbar);

            ImageView add_iv = (ImageView)toolbar.findViewById(R.id.add_iv);
            //ActionBar actionBar = getSupportActionBar();

            add_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SettingsDialog settingsDialog = new SettingsDialog();
                    settingsDialog.setCancelable(true);
                    settingsDialog.show(v.getSupportFragmentManager(), "selection Dialog");
                }
            });

            //ActionBar actionBar = getSupportActionBar();
            //actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);

            NavigationView navigationView = (NavigationView) v.findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(v);

            final Menu menu = navigationView.getMenu();
//            for (int i = 1; i <= 3; i++) {
//                menu.add("Runtime item "+ i);
//                // adding a section and items into it
//
//            }

//            final SubMenu subMenu = menu.addSubMenu("SubMenu Title");
//            for (int i = 1; i <= 2; i++) {
//                subMenu.add("SubMenu Item " + i);
//            }

            DrawerLayout drawerLayout = (DrawerLayout)v.findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(v,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                    super.onDrawerOpened(drawerView);
                }
            };
            //calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();

            return menu;

        }
        catch(Exception ex)
        {
            //Log.v("ss", ex.getMessage());
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static void setSystemLocaleLegacy(Configuration config, Locale locale){
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale){
        config.setLocale(locale);
    }

    @SuppressWarnings("deprecation")
    public static void setLanguage(Context context, String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setSystemLocale(config, locale);
        }else{
            setSystemLocaleLegacy(config, locale);
        }
        context.getApplicationContext().getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

    public static long getDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

        return (elapsedDays * 24 * 60) + (elapsedHours * 60) + elapsedMinutes;
    }

    public static void setAnimation(View view, Context context) {

        Animation animFadein, animslideup;

        animFadein = AnimationUtils.loadAnimation(context,
                R.anim.fade_in);
        animslideup = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);

        final AnimationSet s = new AnimationSet(true);
        s.setInterpolator(new AccelerateInterpolator());

        s.addAnimation(animslideup);
        s.addAnimation(animFadein);
        view.startAnimation(s);
    }

    public static void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public static void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public static String getSharedValue(String key, Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static void setSharedValue(String key, String value, Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(key, value).apply();
    }


    public static int getResizedImageFactor(int width) {
        Log.v("input_width", "" + width);
        int factor = 10;
        if (width < 1000)
            factor = 1;
        if (width > 1000 && width < 2000)
            factor = 2;
        if (width > 2000 && width < 3000)
            factor = 3;
        if (width > 3000 && width < 4000)
            factor = 4;
        if (width > 5000 && width < 6000)
            factor = 5;
        if (width > 6000 && width < 7000)
            factor = 6;
        if (width > 7000 && width < 8000)
            factor = 7;
        if (width > 8000 && width < 9000)
            factor = 8;
        if (width > 9000 && width < 10000)
            factor = 9;

        return factor;
    }

    public static int getImageOrientation(File imageFile) {
        int rotate = 0;
        try {

            //File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }


    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.KITKAT;
        Log.i("URI",uri+"");
        String result = uri+"";
        if (isKitKat && (result.contains("media.documents"))) {

            String[] ary = result.split("/");
            int length = ary.length;
            String imgary = ary[length-1];
            final String[] dat = imgary.split("%3A");

            final String docId = dat[1];
            final String type = dat[0];

            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {

            } else if ("audio".equals(type)) {
            }

            final String selection = "_id=?";
            final String[] selectionArgs = new String[] {
                    dat[1]
            };

            return getDataColumn(context, contentUri, selection, selectionArgs);
        }
        else
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static Bitmap decodeFile(File f) {
        try {
            Bitmap bitmap = null;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inPreferredConfig = Bitmap.Config.ARGB_4444;
            o.inSampleSize = 2;
            o.inDither = true;

            FileInputStream fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();

            int scale = 1;
            final int IMAGE_MAX_SIZE=600;

            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                        (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(f);
            bitmap = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();

            return bitmap;
        }
        catch (Exception ex)
        {

        }
        return  null;
    }

    public static Bitmap rotateMyPhoto(File gallery_picFile, Bitmap photo) {
        Matrix matrix = new Matrix();
        matrix.postRotate(getImageOrientation(gallery_picFile));
        return Bitmap.createBitmap(photo, 0, 0, photo.getWidth(),
                photo.getHeight(), matrix, true);
    }

    public static String EncodeTobase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static Bitmap DecodeToBitmap(String base64)
    {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    public static byte[] getBitmapBytes(Bitmap bitmap)
    {
        int chunkNumbers = 10;
        int bitmapSize = bitmap.getRowBytes() * bitmap.getHeight();
        byte[] imageBytes = new byte[bitmapSize];
        int rows, cols;
        int chunkHeight, chunkWidth;
        rows = cols = (int) Math.sqrt(chunkNumbers);
        chunkHeight = bitmap.getHeight() / rows;
        chunkWidth = bitmap.getWidth() / cols;

        int yCoord = 0;
        int bitmapsSizes = 0;

        for (int x = 0; x < rows; x++)
        {
            int xCoord = 0;
            for (int y = 0; y < cols; y++)
            {
                Bitmap bitmapChunk = Bitmap.createBitmap(bitmap, xCoord, yCoord, chunkWidth, chunkHeight);
                byte[] bitmapArray = getBytesFromBitmapChunk(bitmapChunk);
                System.arraycopy(bitmapArray, 0, imageBytes, bitmapsSizes, bitmapArray.length);
                bitmapsSizes = bitmapsSizes + bitmapArray.length;
                xCoord += chunkWidth;

                bitmapChunk.recycle();
                bitmapChunk = null;
            }
            yCoord += chunkHeight;
        }

        return imageBytes;
    }


    private static byte[] getBytesFromBitmapChunk(Bitmap bitmap)
    {
        int bitmapSize = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmapSize);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byteBuffer.rewind();
        return byteBuffer.array();
    }

//    public static SweetAlertDialog showProgressPrettyDialog(Context context, String message) {
//        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText(message);
//        pDialog.setCancelable(false);
//        return pDialog;
//    }

    static int REQUEST_ID_MULTIPLE_PERMISSIONS;
    public static void getPermissions(Activity activity)
    {
        List<String> listPermissionsNeeded = new ArrayList<>();

        int permissionCamera = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);

        int locationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);

        int storagePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        int permissionWindow = ContextCompat.checkSelfPermission(activity, Manifest.permission.SYSTEM_ALERT_WINDOW);

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionWindow != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SYSTEM_ALERT_WINDOW);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }

}
