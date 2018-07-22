package com.fm2apps.warrantyapp.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fm2apps.warrantyapp.Dialogs.ImagePicker;
import com.fm2apps.warrantyapp.Dialogs.ImagePickerFragment;
import com.fm2apps.warrantyapp.Helpers.Globals;
import com.fm2apps.warrantyapp.Helpers.Models.ProductModel;
import com.fm2apps.warrantyapp.Helpers.Utilities;
import com.fm2apps.warrantyapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;


/**
 * Created by Administrator on 04/07/2016.
 */
public class ReqFragment1 extends Fragment {

//    private static final String TAG = "Barcode-reader";
//
//    // intent request code to handle updating play services if needed.
//    private static final int RC_HANDLE_GMS = 9001;
//
//    // permission request codes need to be < 256
//    private static final int RC_HANDLE_CAMERA_PERM = 2;
//
//    // constants used to pass extra data in the intent
//    public static final String AutoFocus = "AutoFocus";
//    public static final String UseFlash = "UseFlash";
//    public static final String BarcodeObject = "Barcode";
//
//    private CameraSource mCameraSource;
//    private CameraSourcePreview mPreview;
//    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
//
//    // helper objects for detecting taps and pinches.
//    private ScaleGestureDetector scaleGestureDetector;
//    private GestureDetector gestureDetector;

    public ReqFragment1() {

    }

    ImageView iv_pick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.req1_fragment, container, false);
        final TextView barcode_txt = rootView.findViewById(R.id.barcode_txt);
        final TextView name_txt = rootView.findViewById(R.id.name_txt);
        name_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setName(editable.toString());
            }
        });
        final TextView desc_txt = rootView.findViewById(R.id.desc_txt);
        desc_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setDesc(editable.toString());
            }
        });
        final ImageView receipt_img = rootView.findViewById(R.id.receipt_img);
        final LinearLayout lin1 = rootView.findViewById(R.id.lin1);
        iv_pick = rootView.findViewById(R.id.iv_pick);
        if (Globals.productModel != null)
        {
            if(Globals.productModel.getImage64() != null)
            {
                Bitmap decodedByte = Utilities.DecodeToBitmap(Globals.productModel.getImage64());
                iv_pick.setImageBitmap(decodedByte);
            }

            if(Globals.productModel.getReceipt64() != null)
            {
                Bitmap decodedByte = Utilities.DecodeToBitmap(Globals.productModel.getReceipt64());
                receipt_img.setImageBitmap(decodedByte);
                lin1.setVisibility(View.VISIBLE);
                barcode_txt.setVisibility(View.GONE);
            }
            else
            {
                barcode_txt.setVisibility(View.VISIBLE);
                lin1.setVisibility(View.GONE);
            }
        }
        if (getActivity().getIntent() != null && getActivity().getIntent().getExtras() != null)
        {
            String Barcode = getActivity().getIntent().getExtras().getString("Barcode");
            barcode_txt.setText(Barcode);
            barcode_txt.setVisibility(View.VISIBLE);
            lin1.setVisibility(View.GONE);
        }
        //barcode_txt.setText("klkl");
        barcode_txt.setEnabled(false);

        Button button_next = rootView.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (barcode_txt.getText().toString().isEmpty())
//                {
//                    barcode_txt.setError(getResources().getString(R.string.required));
//                    barcode_txt.setFocusable(true);
//                    return;
//                }

                if (name_txt.getText().toString().isEmpty())
                {
                    name_txt.setError(getResources().getString(R.string.required));
                    name_txt.setFocusable(true);
                    return;
                }
                name_txt.setError(null);

                if (desc_txt.getText().toString().isEmpty())
                {
                    desc_txt.setError(getResources().getString(R.string.required));
                    desc_txt.setFocusable(true);
                    return;
                }
                desc_txt.setError(null);

                if (PhotoUrl == null)
                {
                    Toast.makeText(getActivity(), R.string.productimg_required, Toast.LENGTH_LONG).show();
                    return;
                }

                if (Globals.productModel == null)
                {
                    ProductModel productModel = new ProductModel();
                    //productModel.setId(1);
                    productModel.setName(name_txt.getText().toString());
                    productModel.setDesc(desc_txt.getText().toString());
                    productModel.setBarCode(barcode_txt.getText().toString());
                    productModel.setDateCreated(Calendar.getInstance().getTime());
                    productModel.setImage64(PhotoUrl);
                    productModel.setReceipt64("");

                    //productModel.ImageBytes = PhotoBytes;
                    Globals.productModel = productModel;
                }
                else
                {
                    Globals.productModel.setName(name_txt.getText().toString());
                    Globals.productModel.setDesc(desc_txt.getText().toString());
                    Globals.productModel.setBarCode(barcode_txt.getText().toString());
                    Globals.productModel.setDateCreated(Calendar.getInstance().getTime());
                    Globals.productModel.setImage64(PhotoUrl);
                }


                ViewPager _mViewPager = (ViewPager)getActivity().findViewById(R.id.requestviewPager);
                _mViewPager.setCurrentItem(1);
            }
        });

        iv_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagePickerFragment(Globals.CAPTURE_IMAGE_PHOTO_REQUEST_CODE,
                        Globals.ATTATCH_IMAGE_PHOTO_REQUEST_CODE, Globals.FORM_TYPE + "_" + Globals.PHOTO1_NAME,
                        ReqFragment1.this).show();
            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//
//
//        mPreview = (CameraSourcePreview) rootView.findViewById(R.id.preview);
//        mGraphicOverlay = rootView.findViewById(R.id.graphicOverlay);
//
//        // read parameters from the intent used to launch the activity.
//        boolean autoFocus = getActivity().getIntent().getBooleanExtra(AutoFocus, false);
//        boolean useFlash = getActivity().getIntent().getBooleanExtra(UseFlash, false);
//
//        // Check for the camera permission before accessing the camera.  If the
//        // permission is not granted yet, request permission.
//        int rc = ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CAMERA);
//        if (rc == PackageManager.PERMISSION_GRANTED) {
//            createCameraSource(autoFocus, useFlash);
//        } else {
//            requestCameraPermission();
//        }
//
//        gestureDetector = new GestureDetector(this.getActivity(), new CaptureGestureListener());
//        scaleGestureDetector = new ScaleGestureDetector(this.getActivity(), new ScaleListener());
//
//        try
//        {
//            Snackbar.make(mGraphicOverlay, "Tap to capture. Pinch/Stretch to zoom",
//                    Snackbar.LENGTH_LONG)
//                    .show();
//
//        }
//        catch(Exception ex)
//        {
//            String ss = "";
//        }

        return rootView;
    }

    String PhotoUrl;
    byte[] PhotoBytes;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Globals.CAPTURE_IMAGE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Uri pickedImage = data.getData();
                String imagePath = Utilities.getPath(getActivity(), pickedImage);
                File gallery_picFile = new File(imagePath);
                Bitmap photo = Utilities.decodeFile(gallery_picFile);
                int mFactor = Utilities.getResizedImageFactor(photo.getWidth());
                photo = Bitmap.createScaledBitmap(photo, photo.getWidth() / mFactor, photo.getHeight() / mFactor, false);
                ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 20, bmpStream);
                photo = Utilities.rotateMyPhoto(gallery_picFile, photo);

                String tobase64 = Utilities.EncodeTobase64(photo);
                PhotoBytes = Utilities.getBitmapBytes(photo);
                iv_pick.setImageBitmap(photo);
                PhotoUrl = tobase64;
                if (Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setImage64(PhotoUrl);
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else if (requestCode == Globals.ATTATCH_IMAGE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                Bitmap photo = null;
                File gallery_picFile = null;
                ContentResolver cr = getActivity().getContentResolver();

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
                PhotoBytes = Utilities.getBitmapBytes(photo);
                iv_pick.setImageBitmap(photo);
                PhotoUrl = tobase64;
                if (Globals.productModel == null)
                    Globals.productModel = new ProductModel();
                Globals.productModel.setImage64(PhotoUrl);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

//
//    private void requestCameraPermission() {
//        Log.w(TAG, "Camera permission is not granted. Requesting permission");
//
//        final String[] permissions = new String[]{Manifest.permission.CAMERA};
//
//        if (!ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
//                Manifest.permission.CAMERA)) {
//            ActivityCompat.requestPermissions(this.getActivity(), permissions, RC_HANDLE_CAMERA_PERM);
//            return;
//        }
//
//        final Activity thisActivity = this.getActivity();
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ActivityCompat.requestPermissions(thisActivity, permissions,
//                        RC_HANDLE_CAMERA_PERM);
//            }
//        };
//
//        getActivity().findViewById(R.id.topLayout).setOnClickListener(listener);
//        Snackbar.make(mGraphicOverlay, R.string.permission_camera_rationale,
//                Snackbar.LENGTH_INDEFINITE)
//                .setAction(R.string.ok, listener)
//                .show();
//    }
//
//    @SuppressLint("InlinedApi")
//    private void createCameraSource(boolean autoFocus, boolean useFlash) {
//        Context context = getActivity().getApplicationContext();
//
//        // A barcode detector is created to track barcodes.  An associated multi-processor instance
//        // is set to receive the barcode detection results, track the barcodes, and maintain
//        // graphics for each barcode on screen.  The factory is used by the multi-processor to
//        // create a separate tracker instance for each barcode.
//        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();
//        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(mGraphicOverlay, this.getActivity());
//        barcodeDetector.setProcessor(
//                new MultiProcessor.Builder<>(barcodeFactory).build());
//
//        if (!barcodeDetector.isOperational()) {
//            // Note: The first time that an app using the barcode or face API is installed on a
//            // device, GMS will download a native libraries to the device in order to do detection.
//            // Usually this completes before the app is run for the first time.  But if that
//            // download has not yet completed, then the above call will not detect any barcodes
//            // and/or faces.
//            //
//            // isOperational() can be used to check if the required native libraries are currently
//            // available.  The detectors will automatically become operational once the library
//            // downloads complete on device.
//            Log.w(TAG, "Detector dependencies are not yet available.");
//
//            // Check for low storage.  If there is low storage, the native library will not be
//            // downloaded, so detection will not become operational.
//            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
//            boolean hasLowStorage = getActivity().registerReceiver(null, lowstorageFilter) != null;
//
//            if (hasLowStorage) {
//                Toast.makeText(this.getActivity(), R.string.low_storage_error, Toast.LENGTH_LONG).show();
//                Log.w(TAG, getString(R.string.low_storage_error));
//            }
//        }
//
//        // Creates and starts the camera.  Note that this uses a higher resolution in comparison
//        // to other detection examples to enable the barcode detector to detect small barcodes
//        // at long distances.
//        CameraSource.Builder builder = new CameraSource.Builder(getActivity().getApplicationContext(), barcodeDetector)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedPreviewSize(1600, 1024)
//                .setRequestedFps(15.0f);
//
//        // make sure that auto focus is an available option
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            builder = builder.setFocusMode(
//                    autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE : null);
//        }
//
//        mCameraSource = builder
//                .setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
//                .build();
//
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode != RC_HANDLE_CAMERA_PERM) {
//            Log.d(TAG, "Got unexpected permission result: " + requestCode);
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            return;
//        }
//
//        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG, "Camera permission granted - initialize the camera source");
//            // we have permission, so create the camerasource
//            boolean autoFocus = getActivity().getIntent().getBooleanExtra(AutoFocus,false);
//            boolean useFlash = getActivity().getIntent().getBooleanExtra(UseFlash, false);
//            createCameraSource(autoFocus, useFlash);
//            return;
//        }
//
//        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
//                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));
//
//        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                getActivity().finish();
//            }
//        };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
//        builder.setTitle("Multitracker sample")
//                .setMessage(R.string.no_camera_permission)
//                .setPositiveButton(R.string.ok, listener)
//                .show();
//    }
//
//    /**
//     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
//     * (e.g., because onResume was called before the camera source was created), this will be called
//     * again when the camera source is created.
//     */
//    private void startCameraSource() throws SecurityException {
//        // check that the device has play services available.
//        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
//                getActivity().getApplicationContext());
//        if (code != ConnectionResult.SUCCESS) {
//            Dialog dlg =
//                    GoogleApiAvailability.getInstance().getErrorDialog(this.getActivity(), code, RC_HANDLE_GMS);
//            dlg.show();
//        }
//
//        if (mCameraSource != null) {
//            try {
//                mPreview.start(mCameraSource, mGraphicOverlay);
//            } catch (IOException e) {
//                Log.e(TAG, "Unable to start camera source.", e);
//                mCameraSource.release();
//                mCameraSource = null;
//            }
//        }
//    }
//
//    /**
//     * onTap returns the tapped barcode result to the calling Activity.
//     *
//     * @param rawX - the raw position of the tap
//     * @param rawY - the raw position of the tap.
//     * @return true if the activity is ending.
//     */
//    private boolean onTap(float rawX, float rawY) {
//        // Find tap point in preview frame coordinates.
//        int[] location = new int[2];
//        mGraphicOverlay.getLocationOnScreen(location);
//        float x = (rawX - location[0]) / mGraphicOverlay.getWidthScaleFactor();
//        float y = (rawY - location[1]) / mGraphicOverlay.getHeightScaleFactor();
//
//        // Find the barcode whose center is closest to the tapped point.
//        Barcode best = null;
//        float bestDistance = Float.MAX_VALUE;
//        for (BarcodeGraphic graphic : mGraphicOverlay.getGraphics()) {
//            Barcode barcode = graphic.getBarcode();
//            if (barcode.getBoundingBox().contains((int) x, (int) y)) {
//                // Exact hit, no need to keep looking.
//                best = barcode;
//                break;
//            }
//            float dx = x - barcode.getBoundingBox().centerX();
//            float dy = y - barcode.getBoundingBox().centerY();
//            float distance = (dx * dx) + (dy * dy);  // actually squared distance
//            if (distance < bestDistance) {
//                best = barcode;
//                bestDistance = distance;
//            }
//        }
//
//        if (best != null) {
//            Intent data = new Intent();
//            data.putExtra(BarcodeObject, best);
//            getActivity().setResult(CommonStatusCodes.SUCCESS, data);
//            getActivity().finish();
//            return true;
//        }
//        return false;
//    }
//
//    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
//        }
//    }
//
//    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
//
//        /**
//         * Responds to scaling events for a gesture in progress.
//         * Reported by pointer motion.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         * @return Whether or not the detector should consider this event
//         * as handled. If an event was not handled, the detector
//         * will continue to accumulate movement until an event is
//         * handled. This can be useful if an application, for example,
//         * only wants to update scaling factors if the change is
//         * greater than 0.01.
//         */
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            return false;
//        }
//
//        /**
//         * Responds to the beginning of a scaling gesture. Reported by
//         * new pointers going down.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         * @return Whether or not the detector should continue recognizing
//         * this gesture. For example, if a gesture is beginning
//         * with a focal point outside of a region where it makes
//         * sense, onScaleBegin() may return false to ignore the
//         * rest of the gesture.
//         */
//        @Override
//        public boolean onScaleBegin(ScaleGestureDetector detector) {
//            return true;
//        }
//
//        /**
//         * Responds to the end of a scale gesture. Reported by existing
//         * pointers going up.
//         * <p/>
//         * Once a scale has ended, {@link ScaleGestureDetector#getFocusX()}
//         * and {@link ScaleGestureDetector#getFocusY()} will return focal point
//         * of the pointers remaining on the screen.
//         *
//         * @param detector The detector reporting the event - use this to
//         *                 retrieve extended info about event state.
//         */
//        @Override
//        public void onScaleEnd(ScaleGestureDetector detector) {
//            mCameraSource.doZoom(detector.getScaleFactor());
//        }
//    }
//
//    @Override
//    public void onBarcodeDetected(Barcode barcode) {
//        //do something with barcode data returned
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        startCameraSource();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mPreview != null) {
//            mPreview.stop();
//        }
//    }
//
//    /**
//     * Releases the resources associated with the camera source, the associated detectors, and the
//     * rest of the processing pipeline.
//     */
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mPreview != null) {
//            mPreview.release();
//        }
//    }
}
