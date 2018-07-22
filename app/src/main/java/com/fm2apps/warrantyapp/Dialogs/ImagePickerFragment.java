package com.fm2apps.warrantyapp.Dialogs;

//import android.app.Fragment;
import android.support.v4.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohamed on 7/17/2017.
 */

public class ImagePickerFragment extends AlertDialog implements View.OnClickListener {
    private int cameraRequestCode;
    private int attachRequestCode;
    private String imageName;
    private Fragment fragment;
    private Context context = null;

    public ImagePickerFragment(int attachRequestCode, int cameraRequestCode, String imageName,
                               Fragment fragment) {
        super(fragment.getActivity());
        this.cameraRequestCode = cameraRequestCode;
        this.attachRequestCode = attachRequestCode;
        this.imageName = imageName;
        this.fragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickimage_dialog);
        TextView gallery_pick = (TextView) findViewById(R.id.gallery_pick);
        TextView photo_pick = (TextView) findViewById(R.id.photo_pick);
        TextView cancel_pick = (TextView) findViewById(R.id.cancel_pick);

        if (fragment != null)
            context = fragment.getActivity().getApplicationContext();

        int width_dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics());
        getWindow().setLayout(width_dp, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(true);

        assert gallery_pick != null;
        gallery_pick.setOnClickListener(this);
        assert photo_pick != null;
        photo_pick.setOnClickListener(this);
        assert cancel_pick != null;
        cancel_pick.setOnClickListener(this);
    }

    private String cameraDirectoryName;               // for camera with file only
    @Override
    public void onClick(View view) {
        String Id = fragment.getActivity().getResources().getResourceName(view.getId());
        if (Id.contains("gallery")) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            if (fragment != null)
                fragment.startActivityForResult(i, attachRequestCode);

            dismiss();

        } else if (Id.contains("photo")) {
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Uri photoURI = null;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    File photo = new File(Environment.getExternalStorageDirectory(), timeStamp + ".jpg");
                    photoURI = Uri.fromFile(photo);
                    Globals.imageUri = Uri.fromFile(photo);
                    Globals.imagePath = timeStamp;
                } else {
                    //filePath = Utilities.GenerateFilePath(cameraDirectoryName, pickerType == IMAGE ? 1 : 3);
                    //photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", new File(filePath));
                    photoURI = FileProvider.getUriForFile(context, "com.fm2apps.warrantyapp.fileprovider",
                            createImageFile(timeStamp));
                    Globals.imageUri = photoURI;
                    //Globals.imagePath = mCurrentPhotoPath;
                    //Globals.imagePath = timeStamp;
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //String str = Environment.getExternalStorageDirectory().toString() + "/images";
                //File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), timeStamp + ".jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            Uri photoURI = FileProvider.getUriForFile(context,
//                    "com.example.android.fileprovider",
//                    photo);

//            Uri photoURI = FileProvider.getUriForFile(context,
//                    "com.mobileznation.jamalook.fileprovider",
//                    photo);

                //Constant.imageUri = Uri.fromFile(photo);


                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    if (fragment != null)
                        fragment.startActivityForResult(intent, cameraRequestCode);
                } else {
                    Toast.makeText(getOwnerActivity(), "can't find camera", Toast.LENGTH_SHORT).show();
                }

                dismiss();
            } catch (Exception ex) {
                Log.v("saassa", ex.getMessage());
            }
        } else if (Id.contains("cancel")) {
            dismiss();
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile(String timeStamp) throws IOException {
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), timeStamp + ".jpg");
        //File storageDir = new File(Environment.getExternalStoragePublicDirectory(
        //        Environment.DIRECTORY_PICTURES), "Camera");
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        //Globals.imagePath = imageFileName + ".jpg";
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Globals.imagePath = image.getName();
        return image;
    }


}
